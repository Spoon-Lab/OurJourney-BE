package pudding.toy.ourJourney.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class NewProfileResponse {
    Long profileId;
    String nickName;
}
