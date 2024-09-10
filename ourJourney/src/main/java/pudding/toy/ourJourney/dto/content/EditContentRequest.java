package pudding.toy.ourJourney.dto.content;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pudding.toy.ourJourney.entity.ContentTag;
import pudding.toy.ourJourney.entity.Contents;

import java.util.List;

@Data @FieldDefaults(level = AccessLevel.PRIVATE) @AllArgsConstructor
public class EditContentRequest {
    String title;
    String imgUrl;
    List<ContentTag> tags;
}
