package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.auth.AuthResponse;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {
    private final RestTemplate authRestTemplate;
    private final ProfileRepository profileRepository;

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
    public Long currentProfileId(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        log.info("authentication"+authentication.getName());
        return Long.parseLong(authentication.getName());
    }

    public Profile currentProfile(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Long profileId = Long.parseLong(authentication.getName());
        log.info("profileId" + profileId);
        return profileRepository.findById(profileId).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }
}
