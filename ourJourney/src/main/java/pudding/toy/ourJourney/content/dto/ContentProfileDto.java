package pudding.toy.ourJourney.content.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContentProfileDto {
    private Long profileId;
    private String profileImgUrl;
    private String name;
}
