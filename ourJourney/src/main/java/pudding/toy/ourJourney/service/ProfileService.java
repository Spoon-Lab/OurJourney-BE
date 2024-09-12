package pudding.toy.ourJourney.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.dto.auth.ProfileAuthResponseDto;
import pudding.toy.ourJourney.dto.profile.NewProfileResponse;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final List<String> adjectives = Arrays.asList("예쁜", "졸린", "작은", "큰", "빠른", "따뜻한", "밝은", "산뜻한", "사랑스러운", "행복한", "귀여운");

    public NewProfileResponse createProfile(ProfileAuthResponseDto profileAuthResponseDto) {
        profileRepository.findByUserId(profileAuthResponseDto.getId()).ifPresent(profile -> {
            throw new IllegalStateException("프로필이 존재합니다.");
        });
        Profile profile = Profile.builder().userId(profileAuthResponseDto.getId()).nickName(createRandomNickName()).build();
        profileRepository.save(profile);
        return new NewProfileResponse(profile.getId(),profile.getNickName());
    }
    protected String createRandomNickName(){
        int randomIndex = (int)(Math.random()*adjectives.size());
        return adjectives.get(randomIndex)+"푸딩"+(int)(Math.random()*1000);
    }


}
