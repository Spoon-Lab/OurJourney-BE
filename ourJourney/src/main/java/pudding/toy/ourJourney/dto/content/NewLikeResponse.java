package pudding.toy.ourJourney.dto.content;

import lombok.Data;

@Data
public class NewLikeResponse {
    Long likeId;

    public NewLikeResponse(Long likeId) {
        this.likeId = likeId;
    }
}
