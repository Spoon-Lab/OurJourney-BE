package pudding.toy.ourJourney.profile.dto;

import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
public class GetLikeContentsResponse {
    private PageImpl<GetLikesContentsDto> list;

    public GetLikeContentsResponse(PageImpl<GetLikesContentsDto> list) {
        this.list = list;
    }
}
