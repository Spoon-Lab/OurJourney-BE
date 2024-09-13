package pudding.toy.ourJourney.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.ContentsThread;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.entity.Tag;
import pudding.toy.ourJourney.repository.ContentRepository;
import pudding.toy.ourJourney.repository.ProfileRepository;
import pudding.toy.ourJourney.repository.TagRepository;

import java.util.List;

@SpringBootTest
@Transactional
class ThreadServiceTest {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ThreadService threadService;

    @Test
    void createThreadsTest() {
        Profile profile = profileRepository.save(new Profile(2L));
        Contents contents = contentRepository.save(new Contents("title1", null, null, profile));
        Tag tag1 = tagRepository.save(new Tag("tag1"));

        ContentsThread threads = threadService.createThreads(profile, contents.getId(), "texts", List.of(tag1.getId()), "imgUrl");

        Assertions.assertThat(threads.getId()).isNotNull();
    }
}