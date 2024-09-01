package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.dto.ProfileAuthResponseDto;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final ProfileRepository profileRepository;
    /*
    - `get` 내 프로필 보기
    - `get`  남의 프로필 보기 (남의 profile Id)
    - `put` 내 프로필 수정하기
        - 어떤 필드 수정할지 (닉네임, 프로필 사진,한줄소개)
    - `post` 프로필 구독
        - `get` 남의 프로필 보기 → 1차
        - `get` 내가 구독/남이 구독 “숫자” → 1차
     */

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
        return profile.getId();
    }

}
