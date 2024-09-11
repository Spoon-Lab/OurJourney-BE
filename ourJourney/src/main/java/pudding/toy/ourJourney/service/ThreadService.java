package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
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
import pudding.toy.ourJourney.repository.ThreadRepository;

import org.springframework.data.domain.PageImpl;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ThreadService {
    private final ThreadRepository threadRepository;
    private final ContentRepository contentRepository;
    public PageImpl<ListThreadDto> getThreads(Long contentId, Pageable pageable){
        Contents contents = contentRepository.findById(contentId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        List <ListThreadDto> threadDtos = contents.getContentsThreads().stream()
                .map(contentsThread -> new ListThreadDto(
                        contentsThread.getId(),
                        new ProfileThreadDto(contentsThread.getProfile().getId(),contentsThread.getProfile().getProfileImg(),contentsThread.getProfile().getNickName()),
                        contentsThread.getImgUrl(),
                        contentsThread.getTexts(),
                        contentsThread.getTagNames(),
                        contentsThread.getCreatedAt()
                        )
                )
                .toList();
        return new PageImpl<>(threadDtos,pageable,threadDtos.size());
    }
}
