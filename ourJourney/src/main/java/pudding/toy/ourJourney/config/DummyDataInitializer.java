package pudding.toy.ourJourney.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pudding.toy.ourJourney.entity.*;
import pudding.toy.ourJourney.repository.*;

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

        Category category = categoryRepository.save(new Category("국내 여행"));
        categoryRepository.save(new Category("해외 여행"));
        Tag tag = tagRepository.save(new Tag("제주도"));

        Contents contents = contentRepository.save(new Contents(profile, category, "제주도 여행", "jeju.png"));
        contentTagRepository.save(new ContentTag(contents, tag));

        threadRepository.save(new ContentsThread("제주도 여행을 다녀왔어요!", contents));

        commentRepository.save(new Comment(profile, contents, "제주도 여행 너무 좋았어요!"));
    }
}
