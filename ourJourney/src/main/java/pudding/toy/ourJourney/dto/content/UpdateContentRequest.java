package pudding.toy.ourJourney.dto.content;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UpdateContentRequest {
    String title;
    String imgUrl;

    List<Long> tags;
}
