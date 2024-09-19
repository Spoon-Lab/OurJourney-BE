package pudding.toy.ourJourney.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.ProfileRepository;

@Component
@RequiredArgsConstructor
public class DummyDataInitializer {
    private final ProfileRepository profileRepository;
    public Profile dummyProfile;

    @PostConstruct
    public void init() {
        Profile profile = new Profile(
                1L,
                1L,
                "배고픈 무지",
                "profile.png",
                "안녕하세요. 배고픈 무지입니다.",
                null,
                null
        );
        profileRepository.save(profile);
        dummyProfile = profile;
    }
}
