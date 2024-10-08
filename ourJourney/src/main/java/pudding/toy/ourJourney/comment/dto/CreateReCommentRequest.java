package pudding.toy.ourJourney.comment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateReCommentRequest {
    @NotNull
    @Size(min = 1, max = 200)
    private String texts;
}
