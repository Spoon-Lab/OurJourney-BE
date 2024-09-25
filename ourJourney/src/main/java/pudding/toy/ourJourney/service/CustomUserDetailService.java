package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.entity.CustomUserDetail;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailService implements UserDetailsService {
    private final ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Profile profile =  profileRepository.findByUserId(Long.parseLong(userId)).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return new CustomUserDetail(profile);
    }
    public UserDetails loadUserByUserId(Long id) throws UsernameNotFoundException{
        Profile profile =  profileRepository.findByUserId(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return new CustomUserDetail(profile);
    }
}
