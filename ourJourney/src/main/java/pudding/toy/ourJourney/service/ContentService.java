package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.content.*;
import pudding.toy.ourJourney.entity.Category;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.mapper.EditContentsMapper;
import pudding.toy.ourJourney.repository.CategoryRepository;
import pudding.toy.ourJourney.repository.ContentRepository;
import pudding.toy.ourJourney.repository.ContentsQueryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService {
    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final EditContentsMapper contentsMapper;
    private final AttendeeService attendeeService;
    private final TagService tagService;
    private final ContentsQueryRepository contentsQueryRepository;

    public PageImpl<ListContentDto> getAllContents(
            Pageable pageable,
            Optional<Long> categoryId,
            Optional<String> title,
            Optional<List<Long>> tagIds) {
        return contentsQueryRepository.findAll(pageable,categoryId,title,tagIds);
    }

    public Long createContent(CreateContentRequest createContentRequest, Profile profile) {
        Category category = categoryRepository.findById(createContentRequest.getCategoryId()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        Contents content = Contents.builder()
                .title(createContentRequest.getTitle())
                .profile(profile)
                .category(category)
                .build();
        if(createContentRequest.getImgUrl().isPresent()){
            content.setImgUrl(createContentRequest.getImgUrl().orElse(null));
        }
        if(createContentRequest.getProfileIds().isPresent()){
            attendeeService.addAttendee(createContentRequest.getProfileIds().orElse(null),content);
        }
        if(createContentRequest.getTagIds().isPresent()){
            tagService.addContentTag(createContentRequest.getTagIds().orElse(null),content);
        }
        contentRepository.save(content);
        return content.getId();
    }

    public DetailContentResponse getDetailContent(Long contentId) {
        Contents contents = contentRepository.findById(contentId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return DetailContentResponse.from(contents);
    }

    public void updateContent(Long contentId, EditContentRequest editRequestDto) {
        Contents contents = contentRepository.findById(contentId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        contentsMapper.updateEntityFromDto(editRequestDto,contents);
    }

    public void deleteContent(Long contentId) {
        Contents contents = contentRepository.findById(contentId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        contents.remove(LocalDateTime.now());
        contentRepository.save(contents);
    }
}
