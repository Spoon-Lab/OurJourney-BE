package pudding.toy.ourJourney.profile.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Optional;

@Data
public class GetDetailProfileResponse {
    @NotNull
    private Long profileId;
    @Size(max = 32)
    private String nickname;
    @Size(max = 255)
    private Optional<String> imageUrl;
    @Size(max = 255)
    private Optional<String> selfIntroduction;
    private boolean isEditable;
    private int followerNum;
    private int followingNum;

    public GetDetailProfileResponse(Long profileId, String nickname, Optional<String> imageUrl, Optional<String> selfIntroduction, boolean isEditable, int followerNum, int followingNum) {
        this.profileId = profileId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.selfIntroduction = selfIntroduction;
        this.isEditable = isEditable;
        this.followerNum = followerNum;
        this.followingNum = followingNum;
    }

}
