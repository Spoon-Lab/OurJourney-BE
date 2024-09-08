package pudding.toy.ourJourney.dto.profile;

import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
public class GetMyContentsResponse {
    private PageImpl<GetMyContentsDto> contents;

    public GetMyContentsResponse(PageImpl<GetMyContentsDto> contents) {
        this.contents = contents;
    }
}
