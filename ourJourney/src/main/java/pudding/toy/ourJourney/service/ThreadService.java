package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.thread.CreateThreadRequest;
import pudding.toy.ourJourney.dto.thread.ListThreadDto;
import pudding.toy.ourJourney.dto.thread.ProfileThreadDto;
import pudding.toy.ourJourney.dto.thread.UpdateThreadRequest;
import pudding.toy.ourJourney.entity.*;
import pudding.toy.ourJourney.repository.ContentRepository;
import pudding.toy.ourJourney.repository.TagRepository;
import pudding.toy.ourJourney.repository.ThreadRepository;
import pudding.toy.ourJourney.repository.ThreadTagRepository;

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

    @Transactional
    public ContentsThread createThreads(Profile profile, Long contentId, CreateThreadRequest createThreadRequest) {
        Contents content = getContent(contentId);
        ContentsThread thread = new ContentsThread(createThreadRequest.getTexts(), profile, content);

        createThreadRequest.getThreadImg().ifPresent(thread::setImgUrl);
        createThreadRequest.getTagIds().ifPresent(tagIds -> {
            List<Tag> tags = tagRepository.findAllById(tagIds);
            List<ThreadTag> threadTags = tags.stream().map(tag -> new ThreadTag(thread, tag)).toList();
            threadTagRepository.saveAll(threadTags);
        });
        threadRepository.save(thread);
        return thread;
    }

    @Transactional
    public ContentsThread updateThread(Long contentId, Long threadId, UpdateThreadRequest body, Profile profile) {
        Contents contents = getContent(contentId);
        ContentsThread thread = getThread(threadId);
        validateThreadBelongsToContent(thread, contents);
        if (!thread.getProfile().equals(profile)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "수정 권한이 없습니다.");
        }

        body.getThreadImg().ifPresent(thread::setImgUrl);
        body.getTexts().ifPresent(thread::setTexts);
        body.getTags().ifPresent(tagIds -> {
            threadTagRepository.deleteAllByContentsThreadId(threadId);

            if (tagIds != null) {
                List<Tag> tags = tagRepository.findAllById(tagIds);
                List<ThreadTag> threadTags = tags.stream().map(tag -> new ThreadTag(thread, tag)).toList();
                threadTagRepository.saveAll(threadTags);
            }
        });

        return threadRepository.save(thread);
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
