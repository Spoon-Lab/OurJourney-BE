package pudding.toy.ourJourney.dto.thread;

import org.springframework.data.domain.PageImpl;
import pudding.toy.ourJourney.dto.content.ContentResponseDto;

public class GetThreadResponse {
    private PageImpl<ThreadResponseDto> list;
    public GetThreadResponse(PageImpl<ThreadResponseDto> list){
        this.list = list;
    }
}
