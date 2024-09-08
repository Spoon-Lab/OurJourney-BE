package pudding.toy.ourJourney.dto.profile;

import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
public class GetMyCommentsResponse {
    private PageImpl<GetMyCommentsDto> list;

    public GetMyCommentsResponse(PageImpl<GetMyCommentsDto> list) {
        this.list = list;
    }
}
