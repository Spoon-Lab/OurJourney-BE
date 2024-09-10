package pudding.toy.ourJourney.dto.thread;

import org.springframework.data.domain.PageImpl;

public class GetThreadResponse {
    private PageImpl<ListThreadDto> list;

    public GetThreadResponse(PageImpl<ListThreadDto> list) {
        this.list = list;
    }
}
