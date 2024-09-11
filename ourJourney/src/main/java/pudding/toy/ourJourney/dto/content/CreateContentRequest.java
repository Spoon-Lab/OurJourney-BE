package pudding.toy.ourJourney.dto.content;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class CreateContentRequest {
    @NotNull @Size(min = 1, max = 30, message = "제목은 1 ~ 30자 이여야 합니다!")
    String title;
    @NotNull
    Long categoryId;
    @Schema(implementation = String.class)
    JsonNullable<String> imgUrl;

    @ArraySchema(schema = @Schema(implementation = Long.class))
    JsonNullable<List<Long>> profileIds;

    @ArraySchema(schema = @Schema(implementation = Long.class))
    JsonNullable<List<Long>> tagIds;
}
