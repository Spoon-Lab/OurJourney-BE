package pudding.toy.ourJourney.dto.content;

import org.springframework.data.domain.PageImpl;

public class GetContentResponse {
    private PageImpl<ListContentDto> list;
    public GetContentResponse(PageImpl<ListContentDto> list){
        this.list = list;
    }
}
