package pudding.toy.ourJourney.dto.comment;

import lombok.Data;

@Data
public class CreateCommentResponse {
    private Long commentId;

    public CreateCommentResponse(Long commentId) {
        this.commentId = commentId;
    }
}
