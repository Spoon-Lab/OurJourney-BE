package pudding.toy.ourJourney.dto.content;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.openapitools.jackson.nullable.JsonNullable;
import pudding.toy.ourJourney.entity.ContentTag;
import pudding.toy.ourJourney.entity.Contents;

import java.util.List;

@Data @FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor @Builder
public class EditContentRequest {
    String title;
    @Schema(implementation = String.class)
    JsonNullable<String> imgUrl;

    @ArraySchema(schema = @Schema(implementation = Long.class))
    JsonNullable<List<Long>> tags;
}
