package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.dto.content.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService {
    public PageImpl<ContentResponseDto> getAllContents(ContentRequestDto contentRequestDto) {
        List<ContentResponseDto> list = List.of(new ContentResponseDto());
        return new PageImpl<>(list);
    }

    public CreateContentResponse createContent(CreateContentRequest createContentRequest) {
        return new CreateContentResponse();
    }

    public DetailContentResponseDto getOneContent(Long contentId) {
        return new DetailContentResponseDto();
    }

    public ContentResponseDto editOneContent(Long contentId, ContentEditRequestDto contentEditRequestDto) {
        return new ContentResponseDto();
    }

    public void deleteOneContent(Long contentId) {

    }
}
