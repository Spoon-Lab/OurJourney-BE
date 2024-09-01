package pudding.toy.ourJourney.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pudding.toy.ourJourney.entity.Profile;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProfileAuthResponseDto { //django 가 보내주는 요청에 대한 응답
    @JsonProperty("user_id")
    int id; //user pk
    public Profile toEntity(){
        return Profile.builder().userId(id).build();
    }
}
