package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.auth.AuthResponse;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final RestTemplate authRestTemplate;

    public AuthResponse validateAuth(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분을 추출
            return getAuth(accessToken);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    public AuthResponse getAuth(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<AuthResponse> response = authRestTemplate.exchange("/auth/certificate", HttpMethod.GET, entity, AuthResponse.class);
        if(response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return response.getBody();
    }
}
