package pudding.toy.ourJourney.dto.thread;

import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
public class GetThreadResponse {
    private PageImpl<ListThreadDto> list;

    public GetThreadResponse(PageImpl<ListThreadDto> list) {
        this.list = list;
    }
}
