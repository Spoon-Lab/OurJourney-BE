package pudding.toy.ourJourney.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;
import pudding.toy.ourJourney.entity.*;
import pudding.toy.ourJourney.repository.*;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DummyDataInitializer {
    private final ProfileRepository profileRepository;
    private final CategoryRepository categoryRepository;
    private final ContentRepository contentRepository;
    private final ThreadRepository threadRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;
    private final ContentTagRepository contentTagRepository;
    private final Environment environment;
    private final ThreadTagRepository threadTagRepository;

    public Profile dummyProfile;

    @PostConstruct
    public void init() {

        Optional<Profile> foundProfile = profileRepository.findById(1L);
        if (foundProfile.isEmpty()) {
            Profile profile = new Profile(
                    1L,
                    "배고픈 무지",
                    "profile.png",
                    "안녕하세요. 배고픈 무지입니다."
            );
            profileRepository.save(profile);
            dummyProfile = profile;
        } else {
            dummyProfile = foundProfile.get();
        }


        if (environment.acceptsProfiles(Profiles.of("development"))) {
            Category category = categoryRepository.save(new Category("국내 여행"));
            categoryRepository.save(new Category("해외 여행"));
            Tag tag = tagRepository.save(new Tag("제주도"));

            Contents contents = contentRepository.save(new Contents(dummyProfile, category, "제주도 여행", "jeju.png"));
            contentTagRepository.save(new ContentTag(contents, tag));

            ContentsThread contentsThread = threadRepository.save(new ContentsThread("제주도 여행을 다녀왔어요!", dummyProfile, contents));
            threadRepository.save(contentsThread);

            commentRepository.save(new Comment(dummyProfile, contents, "제주도 여행 너무 좋았어요!"));

        }

    }
}
