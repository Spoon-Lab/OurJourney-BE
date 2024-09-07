package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.dto.*;

@Service @RequiredArgsConstructor @Transactional
public class ContentService {
    public ContentResponseDto getAllContents(ContentRequestDto contentRequestDto){
        return new ContentResponseDto();
    }
    public ContentPostResponseDto createContent(ContentPostRequestDto contentPostRequestDto){
        return new ContentPostResponseDto();
    }
    public ContentOneResponseDto getOneContent(Long contentId){
        return new ContentOneResponseDto();
    }
    public ContentResponseDto editOneContent(Long contentId, ContentEditRequestDto contentEditRequestDto){
        return new ContentResponseDto();
    }
    public void deleteOneContent(Long contentId){

    }
}
