package pudding.toy.ourJourney.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProfileAuthRequest { //django 가 보내주는 요청에 대한 응답이 유저 pk
    @JsonProperty("user_id")
    Long id; //user pk
}
