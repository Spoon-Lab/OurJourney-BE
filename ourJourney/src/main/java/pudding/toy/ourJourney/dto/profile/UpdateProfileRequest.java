package pudding.toy.ourJourney.dto.profile;

import lombok.Data;

import java.util.Optional;

@Data
public class UpdateProfileRequest {
    private Optional<String> nickname;
    private Optional<String> imageUrl;
    private Optional<String> selfIntroduction;
}
