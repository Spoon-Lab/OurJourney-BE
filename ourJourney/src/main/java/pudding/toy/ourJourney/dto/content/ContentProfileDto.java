package pudding.toy.ourJourney.dto.content;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContentProfileDto {
    private Long profileId;
    private String profileImgUrl;
    private String name;
}
