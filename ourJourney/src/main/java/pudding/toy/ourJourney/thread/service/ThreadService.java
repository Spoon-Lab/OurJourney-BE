package pudding.toy.ourJourney.thread.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.content.repository.ContentRepository;
import pudding.toy.ourJourney.profile.entity.Profile;
import pudding.toy.ourJourney.tags.entity.Tag;
import pudding.toy.ourJourney.tags.entity.ThreadTag;
import pudding.toy.ourJourney.tags.repository.TagRepository;
import pudding.toy.ourJourney.tags.repository.ThreadTagRepository;
import pudding.toy.ourJourney.thread.dto.*;
import pudding.toy.ourJourney.thread.entity.ContentsThread;
import pudding.toy.ourJourney.thread.repository.ThreadRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ThreadService {
    private final ThreadRepository threadRepository;
    private final ContentRepository contentRepository;
    private final TagRepository tagRepository;
    private final ThreadTagRepository threadTagRepository;

    public PageImpl<ListThreadDto> getThreads(Long contentId, Pageable pageable, Optional<Profile> profile) {
        Contents contents = getContent(contentId);
        Page<ContentsThread> pageThreads = threadRepository.findByContentsAndDeletedAtIsNull(pageable, contents);
        Long totalCount = threadRepository.countByContents(contents);

        Boolean isEditable = profile.filter(value -> contents.getProfile().getId().equals(value.getId())).isPresent();
        Boolean isRemovable = profile.filter(value -> contents.getProfile().getId().equals(value.getId())).isPresent();

        List<ListThreadDto> threadDtos = pageThreads.stream()
                .map(contentsThread -> new ListThreadDto(
                                contentsThread.getId(),
                                new ProfileThreadDto(
                                        contentsThread.getProfile().getId(),
                                        contentsThread.getProfile().getProfileImg(),
                                        contentsThread.getProfile().getNickName()
                                ),
                                contentsThread.getImgUrl(),
                                contentsThread.getTexts(),
                                contentsThread.getTagNames(),
                                contentsThread.getCreatedAt(),
                                isEditable,
                                isRemovable
                        )
                )
                .toList();

        return new PageImpl<>(threadDtos, pageable, totalCount);
    }

    public ThreadDetailResponse getThreadsDetail(Long contentId, Long threadId, Optional<Profile> profile) {
        Contents contents = getContent(contentId);
        ContentsThread contentsThread = getThread(threadId);
        validateThreadBelongsToContent(contentsThread, contents);
        Optional<List<ThreadTag>> threadTags = threadTagRepository.findAllByContentsThread(contentsThread);

        Boolean isEditable = profile.filter(value -> contents.getProfile().getId().equals(value.getId())).isPresent();
        Boolean isRemovable = profile.filter(value -> contents.getProfile().getId().equals(value.getId())).isPresent();

        return new ThreadDetailResponse(contentId, contentsThread, threadTags, isEditable, isRemovable);
    }

    @Transactional
    public ContentsThread createThreads(Profile profile, Long contentId, CreateThreadRequest createThreadRequest) {
        Contents content = getContent(contentId);
        ContentsThread thread = new ContentsThread(createThreadRequest.getTexts(), profile, content);
        threadRepository.save(thread);
        createThreadRequest.getThreadImg().ifPresent(thread::setImgUrl);
        createThreadRequest.getTagIds()
                .filter(tagIds -> !tagIds.isEmpty())
                .ifPresent(tagIds -> addThreadTag(tagIds, thread));
        threadRepository.save(thread);
        return thread;
    }

    private void addThreadTag(List<Long> tagIds, ContentsThread thread) {
        List<Tag> tags = tagRepository.findAllById(tagIds);
        if (tagIds.size() != tags.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 태그가 없습니다.");
        }
        List<ThreadTag> threadTags = tags.stream()
                .map(tag -> new ThreadTag(thread, tag))
                .toList();
        threadTagRepository.saveAll(threadTags);

    }

    @Transactional
    public ContentsThread updateThread(Long contentId, Long threadId, UpdateThreadRequest body, Profile profile) {
        Contents contents = getContent(contentId);
        ContentsThread thread = getThread(threadId);
        validateThreadBelongsToContent(thread, contents);
        if (!thread.getProfile().equals(profile)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "수정 권한이 없습니다.");
        }

        Optional.ofNullable(body.getThreadImg()).ifPresent(thread::setImgUrl);
        Optional.ofNullable(body.getTexts()).ifPresent(thread::setTexts);
        updateThreadTags(body, thread);

        return threadRepository.save(thread);
    }

    private void updateThreadTags(UpdateThreadRequest editRequestDto, ContentsThread thread) {
        Optional.ofNullable(editRequestDto.getTags())
                .ifPresent(newTags -> {
                    Optional<List<ThreadTag>> existingThreadTags = threadTagRepository.findAllByContentsThread(thread);
                    if (existingThreadTags.isPresent()) {
                        createNewTagsAndDeleteOldTags(editRequestDto, thread, existingThreadTags);
                        return;
                    }
                    createNewUpdateThreadTag(editRequestDto, thread, existingThreadTags);
                });
    }

    private void createNewTagsAndDeleteOldTags(UpdateThreadRequest editRequestDto, ContentsThread thread, Optional<List<ThreadTag>> existingThreadTags) {
        List<Tag> newTagEntities = tagRepository.findAllById(editRequestDto.getTags()).stream()
                .filter(tag -> !existingThreadTags.equals(tag))
                .toList();
        List<ThreadTag> tagsToDelete = existingThreadTags.get().stream()
                .filter(contentTag -> !newTagEntities.equals(contentTag))
                .toList();
        threadTagRepository.deleteAll(tagsToDelete);
        List<ThreadTag> threadTags = newTagEntities.stream()
                .map(tag -> new ThreadTag(thread, tag))
                .toList();
        threadTagRepository.saveAll(threadTags);
    }

    private void createNewUpdateThreadTag(UpdateThreadRequest editRequestDto, ContentsThread thread, Optional<List<ThreadTag>> existingThreadTags) {
        List<Tag> newTagEntities = tagRepository.findAllById(editRequestDto.getTags()).stream()
                .filter(tag -> !existingThreadTags.equals(tag))
                .toList();
        List<ThreadTag> threadTags = newTagEntities.stream()
                .map(tag -> new ThreadTag(thread, tag))
                .toList();
        threadTagRepository.saveAll(threadTags);
    }

    public void deleteThread(Long contentId, Long threadId, Profile profile) {
        Contents contents = getContent(contentId);
        ContentsThread thread = getThread(threadId);
        validateThreadBelongsToContent(thread, contents);
        if (!thread.getProfile().equals(profile)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "삭제 권한이 없습니다.");
        }
        thread.remove();
        threadRepository.save(thread);
    }

    private void validateThreadBelongsToContent(ContentsThread thread, Contents contents) {
        if (!thread.getContents().equals(contents)) {
            throw new IllegalStateException("글에 속한 타래가 아닙니다.");
        }
    }

    private ContentsThread getThread(Long threadId) {
        return threadRepository.findByIdAndDeletedAtIsNull(threadId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 타래가 없습니다."));
    }

    private Contents getContent(Long contentId) {
        return contentRepository.findByIdAndDeletedAtIsNull(contentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 타래가 없습니다."));
    }
}
