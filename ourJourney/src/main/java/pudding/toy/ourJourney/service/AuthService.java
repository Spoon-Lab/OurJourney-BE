package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService { //django to auth
    private final ProfileRepository profileRepository;
    public Boolean validateAuth(String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분을 추출
        }
        //todo: auth
        return true;
    }

}
