package pudding.toy.ourJourney.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.global.entity.CustomUserDetail;
import pudding.toy.ourJourney.profile.entity.Profile;
import pudding.toy.ourJourney.profile.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailService implements UserDetailsService {
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByUserId(Long.parseLong(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 사용자입니다."));
        return new CustomUserDetail(profile);
    }

    public UserDetails loadUserByUserId(Long id) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByUserId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 사용자입니다."));
        return new CustomUserDetail(profile);
    }
}
