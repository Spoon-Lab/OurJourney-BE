package pudding.toy.ourJourney.profile.dto;

import lombok.Data;

@Data
public class CreateProfileRequest {
    private Long userId;
    private String email;
}