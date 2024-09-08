package pudding.toy.ourJourney.dto.profile;

import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
public class GetMyCommentsResponse {
    private PageImpl<GetMyCommentsDto> contents;

    public GetMyCommentsResponse(PageImpl<GetMyCommentsDto> contents) {
        this.contents = contents;
    }
}
