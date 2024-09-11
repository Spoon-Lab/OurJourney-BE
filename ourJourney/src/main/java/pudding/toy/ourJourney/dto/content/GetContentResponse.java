package pudding.toy.ourJourney.dto.content;

import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
public class GetContentResponse {
    private PageImpl<ListContentDto> list;

    public GetContentResponse(PageImpl<ListContentDto> list) {
        this.list = list;
    }
}
