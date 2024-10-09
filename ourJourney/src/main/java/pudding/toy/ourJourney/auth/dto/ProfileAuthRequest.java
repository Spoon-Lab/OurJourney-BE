package pudding.toy.ourJourney.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileAuthRequest { //django 가 보내주는 요청에 대한 응답이 유저 pk
    Long id; //user pk
}
