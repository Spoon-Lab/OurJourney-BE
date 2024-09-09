package pudding.toy.ourJourney.dto.content;

import lombok.Data;
import pudding.toy.ourJourney.entity.ContentTag;
import pudding.toy.ourJourney.entity.Tag;

import java.util.List;

@Data
public class EditContentRequest {
    String title;
    String imgUrl;
    List<Tag> tags;
}
