package pudding.toy.ourJourney.content.dto;

import lombok.Data;

@Data
public class NewLikeResponse {
    Long likeId;

    public NewLikeResponse(Long likeId) {
        this.likeId = likeId;
    }
}
