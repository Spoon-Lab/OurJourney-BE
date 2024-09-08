package pudding.toy.ourJourney.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pudding.toy.ourJourney.dto.auth.ProfileAuthResponseDto;
import pudding.toy.ourJourney.entity.Profile;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProfileEditRequestDto { //todo: 장고와 변수 이름을 맞출 필요가 있습니다.
    @JsonProperty("user_id")
    int id; //user pk
    public static Profile toEntity(ProfileAuthResponseDto profileAuthResponseDto){
        return Profile.builder().userId(profileAuthResponseDto.getId()).build();
    }
}
