package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pudding.toy.ourJourney.dto.auth.AuthResponse;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService { //django to auth
    private final ProfileRepository profileRepository;
    private final RestTemplate authRestTemplate;

    public Boolean validateAuth(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분을 추출
        }
        //todo: auth
        return true;
    }

    public AuthResponse getAuth(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<AuthResponse> response = authRestTemplate.exchange("/auth/certificate", HttpMethod.GET, entity, AuthResponse.class);
        return response.getBody();
    }

}
