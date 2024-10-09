package pudding.toy.ourJourney.comment.dto;

import lombok.Data;

@Data
public class CreateCommentResponse {
    private Long commentId;

    public CreateCommentResponse(Long commentId) {
        this.commentId = commentId;
    }
}
