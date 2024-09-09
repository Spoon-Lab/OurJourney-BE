package pudding.toy.ourJourney.dto.content;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data @FieldDefaults(level = AccessLevel.PRIVATE)
public class ListContentDto {
    Long contentId;
    String title;
    String postImg;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
