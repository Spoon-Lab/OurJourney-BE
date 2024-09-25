package pudding.toy.ourJourney.dto.content;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class CreateContentRequest {
    @NotNull @Size(min = 1, max = 30, message = "제목은 1 ~ 30자 이여야 합니다!")
    String title;
    @NotNull
    Long categoryId;
    Optional<String> imgUrl;

    Optional<List<Long>> profileIds;

    Optional<List<Long>> tagIds;
}