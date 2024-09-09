package pudding.toy.ourJourney.dto.content;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateContentRequest {
    @NotNull @Size(min = 1, max = 10, message = "제목은 1 ~ 10자 이여야 합니다!")
    String title;
    @NotNull
    Long categoryId;
    @NotNull
    List<Long> attendeeIds;
    List<Long> tagIds;
}
