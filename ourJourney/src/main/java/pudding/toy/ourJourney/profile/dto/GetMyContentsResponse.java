package pudding.toy.ourJourney.profile.dto;

import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
public class GetMyContentsResponse {
    private PageImpl<GetMyContentsDto> list;

    public GetMyContentsResponse(PageImpl<GetMyContentsDto> list) {
        this.list = list;
    }
}
