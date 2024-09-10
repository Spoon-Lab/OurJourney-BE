package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.content.*;
import pudding.toy.ourJourney.entity.Category;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.mapper.ContentsMapper;
import pudding.toy.ourJourney.repository.CategoryRepository;
import pudding.toy.ourJourney.repository.ContentRepository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService {
    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final ContentsMapper contentsMapper;
    public PageImpl<ListContentDto> getAllContents(Pageable pageable,Optional<Long> categoryId, Optional<String> content, Optional<List<Long>> tagIds) {
        List<ListContentDto> list = List.of(new ListContentDto());
        return new PageImpl<>(list);
    }

    public Long createContent(CreateContentRequest createContentRequest) {
        Category category = categoryRepository.findById(createContentRequest.getCategoryId()).orElseThrow(
                ()-> new IllegalArgumentException("category 없음")
        );
        Contents content = Contents.builder()
                .title(createContentRequest.getTitle())
                .category(category)
                .build();
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
        contents.setDeletedAt(LocalDateTime.now());
        contentRepository.save(contents);
    }
}
