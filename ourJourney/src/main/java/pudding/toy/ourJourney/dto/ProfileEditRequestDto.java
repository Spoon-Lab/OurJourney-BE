package pudding.toy.ourJourney.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pudding.toy.ourJourney.entity.Profile;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProfileRequestDto { //todo: 장고와 변수 이름을 맞출 필요가 있습니다.
    @JsonProperty("user_id")
    int id; //user pk
    @JsonProperty("nick_name")
    String nickName;//닉네임
    public static Profile toEntity(ProfileRequestDto profileRequestDto){
        return Profile.builder().userId(profileRequestDto.getId()).nickName(profileRequestDto.getNickName()).build();
    }
}
