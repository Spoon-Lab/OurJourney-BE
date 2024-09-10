package pudding.toy.ourJourney.dto.thread;

import org.springframework.data.domain.PageImpl;

public class GetThreadResponse {
    private PageImpl<GetThreadDto> list;

    public GetThreadResponse(PageImpl<GetThreadDto> list) {
        this.list = list;
    }
}
