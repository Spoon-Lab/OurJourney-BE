package pudding.toy.ourJourney.dto.content;

import org.springframework.data.domain.PageImpl;

public class GetContentResponse {
    private PageImpl<ContentResponseDto> list;
    public GetContentResponse(PageImpl<ContentResponseDto> list){
        this.list = list;
    }
}
