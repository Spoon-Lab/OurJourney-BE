package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.thread.ListThreadDto;
import pudding.toy.ourJourney.dto.thread.ProfileThreadDto;
import pudding.toy.ourJourney.entity.*;
import pudding.toy.ourJourney.repository.ContentRepository;
import pudding.toy.ourJourney.repository.TagRepository;
import pudding.toy.ourJourney.repository.ThreadRepository;
import pudding.toy.ourJourney.repository.ThreadTagRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ThreadService {
    private final ThreadRepository threadRepository;
    private final ContentRepository contentRepository;
    private final TagRepository tagRepository;
    private final ThreadTagRepository threadTagRepository;

    public PageImpl<ListThreadDto> getThreads(Long contentId, Pageable pageable) {
        Contents contents = getContent(contentId);
        Page<ContentsThread> pageThreads = threadRepository.findByContents(pageable, contents);
        Long totalCount = threadRepository.countByContents(contents);
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
                                contentsThread.getCreatedAt()
                        )
                )
                .toList();

        return new PageImpl<>(threadDtos, pageable, totalCount);
    }

    public ContentsThread createThreads(Profile profile, Long contentId, String texts, List<Long> tagIds, String threadImg) {
        Contents content = getContent(contentId);
        List<Tag> tags = tagRepository.findAllById(tagIds);

        ContentsThread thread = new ContentsThread(texts, threadImg, profile, content);
        threadRepository.save(thread);

        List<ThreadTag> threadTags = tags.stream().map(tag -> new ThreadTag(thread, tag)).toList();
        threadTagRepository.saveAll(threadTags);

        return thread;
    }

    public void deleteThread(Long contentId, Long threadId) {
        Contents contents = getContent(contentId);
        ContentsThread thread = getThread(threadId);
        validateThreadBelongsToContent(thread, contents);

        thread.remove();
        threadRepository.save(thread);
    }

    private void validateThreadBelongsToContent(ContentsThread thread, Contents contents) {
        if (!thread.getContents().equals(contents)) {
            throw new IllegalStateException("글에 속한 타래가 아닙니다.");
        }
    }

    private ContentsThread getThread(Long threadId) {
        return threadRepository.findById(threadId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Contents getContent(Long contentId) {
        return contentRepository.findById(contentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
