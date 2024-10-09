package pudding.toy.ourJourney.comment.dto;

import lombok.Data;

@Data
public class CreateReCommentResponse {
    private Long commentId;

    public CreateReCommentResponse(Long commentId) {
        this.commentId = commentId;
    }
}
