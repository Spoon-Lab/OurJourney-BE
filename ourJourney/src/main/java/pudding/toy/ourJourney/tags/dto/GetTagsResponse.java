package pudding.toy.ourJourney.tags.dto;

import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
public class GetTagsResponse {
    PageImpl<GetTagsDto> list;

    public GetTagsResponse(PageImpl<GetTagsDto> list) {
        this.list = list;
    }
}
