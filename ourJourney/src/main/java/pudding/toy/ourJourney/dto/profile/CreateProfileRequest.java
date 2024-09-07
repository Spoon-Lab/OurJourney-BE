package pudding.toy.ourJourney.dto.profile;

import lombok.Data;

@Data
public class CreateProfileRequest {
    private Long userId;
    private String email;
}