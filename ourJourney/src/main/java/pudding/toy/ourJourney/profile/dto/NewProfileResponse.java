package pudding.toy.ourJourney.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewProfileResponse {
    Long profileId;
    String nickName;
}
