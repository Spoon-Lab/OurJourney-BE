package pudding.toy.ourJourney.dto.profile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Optional;

@Data
public class GetDetailProfileResponse {
    @NotNull
    private Long profileId;
    @Size(max = 32)
    private Optional<String> nickname;
    @Size(max = 255)
    private Optional<String> imageUrl;
    @Size(max = 255)
    private Optional<String> selfIntroduction;

    public GetDetailProfileResponse(Long profileId, Optional<String> nickname, Optional<String> imageUrl, Optional<String> selfIntroduction) {
        this.profileId = profileId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.selfIntroduction = selfIntroduction;
    }

}
