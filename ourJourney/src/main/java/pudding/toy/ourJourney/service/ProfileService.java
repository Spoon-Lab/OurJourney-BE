package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.dto.auth.ProfileAuthResponseDto;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final ProfileRepository profileRepository;
    /**
     * 유저의 프로필을 생성한다.
     * @param profileAuthResponseDto
     * @return Long pk
     */
    public Long createProfile(ProfileAuthResponseDto profileAuthResponseDto) {
        profileRepository.findByUserId(profileAuthResponseDto.getId()).ifPresent(profile -> {
            throw new IllegalStateException("프로필이 존재합니다.");
        });
        Profile profile = profileAuthResponseDto.toEntity();
        //닉네임 무작위로 넣는? 그리고 NuLL -> imgUrl에는 NULL
        return profile.getId();
    }

}
