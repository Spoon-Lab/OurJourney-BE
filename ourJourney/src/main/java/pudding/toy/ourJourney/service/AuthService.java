package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.auth.AuthResponse;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {
    private final RestTemplate authRestTemplate;
    private final ProfileRepository profileRepository;

    public AuthResponse validateAuth(String authorizationHeader) {
        if (authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분을 추출
            return getAuthForAuthorize(accessToken);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "형식이 맞지 않은 토큰입니다.");
    }

    public AuthResponse getAuthForAuthorize(String accessToken) {
        try {
            return getAuth(accessToken); // 정상적으로 토큰을 처리
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않거나 만료된 토큰입니다.");
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    private AuthResponse getAuth(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<AuthResponse> response = authRestTemplate.exchange("/auth/certificate", HttpMethod.GET, entity, AuthResponse.class);
        return response.getBody();
    }

    public Optional<Profile> getProfileWithAnonymous(String headerToken) {
        try {
            if (headerToken == null || !headerToken.startsWith("Bearer ")) {
                return Optional.empty();
            }
            String accessToken = headerToken.substring(7);
            AuthResponse authResponse = getAuth(accessToken);
            Optional<Profile> profile = profileRepository.findByUserId(authResponse.getUserId());
            System.out.println(profile.get().getId());
            return profile;
        } catch (HttpClientErrorException e) {
            log.info(e.getMessage());
            return Optional.empty();
        }
    }

    public Long currentProfileId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        log.info("authentication" + authentication.getName());
        return Long.parseLong(authentication.getName());
    }

    public Optional<Profile> currentProfile() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }
        Long profileId = Long.parseLong(authentication.getName());
        System.out.println("profileId" + profileId);
        return profileRepository.findById(profileId);
    }

    public Profile getProfileWithAuthorize() {
        return currentProfile().orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."));
    }
}
