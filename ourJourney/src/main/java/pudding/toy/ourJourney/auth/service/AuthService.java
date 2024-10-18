package pudding.toy.ourJourney.auth.service;


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
import pudding.toy.ourJourney.auth.dto.AuthResponse;
import pudding.toy.ourJourney.global.error.ErrorCode;
import pudding.toy.ourJourney.global.exception.CustomException;
import pudding.toy.ourJourney.profile.entity.Profile;
import pudding.toy.ourJourney.profile.repository.ProfileRepository;

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
        throw new CustomException(ErrorCode.BAD_REQUEST_400);
    }

    public AuthResponse getAuthForAuthorize(String accessToken) {
        try {
            return getAuth(accessToken); // 정상적으로 토큰을 처리
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_401);
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
            return profile;
        } catch (HttpClientErrorException e) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_401);
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
        return currentProfile().orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED_401));
    }
}
