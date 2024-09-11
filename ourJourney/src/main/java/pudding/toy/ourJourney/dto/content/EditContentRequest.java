package pudding.toy.ourJourney.dto.content;

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
    JsonNullable<String> imgUrl;
    JsonNullable<List<ContentTag>> tags;
}
