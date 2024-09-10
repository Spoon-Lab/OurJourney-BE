package pudding.toy.ourJourney.dto.comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCommentRequest {
    @NotNull
    @Size(min = 1, max = 200)
    private String texts;
}
