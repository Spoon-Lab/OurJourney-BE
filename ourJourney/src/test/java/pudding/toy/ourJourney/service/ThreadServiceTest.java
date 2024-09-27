package pudding.toy.ourJourney.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.dto.thread.CreateThreadRequest;
import pudding.toy.ourJourney.dto.thread.UpdateThreadRequest;
import pudding.toy.ourJourney.entity.*;
import pudding.toy.ourJourney.repository.*;

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
    ThreadRepository threadRepository;

    @Autowired
    ThreadTagRepository threadTagRepository;

    @Autowired
    ThreadService threadService;

    @Test
    void createThreadsTest() {
        Profile profile = profileRepository.save(new Profile(2L));
        Contents contents = contentRepository.save(new Contents("title1", null, null, profile));
        Tag tag1 = tagRepository.save(new Tag("tag1"));
        CreateThreadRequest createThreadRequest = new CreateThreadRequest();
        ContentsThread threads = threadService.createThreads(profile, contents.getId(), createThreadRequest);
        Assertions.assertThat(threads.getId()).isNotNull();
    }

    @Test
    void updateThreadTest() {
        // given
        Profile profile = profileRepository.save(new Profile(2L));
        Contents contents = contentRepository.save(new Contents("title1", null, null, profile));
        Tag tag1 = tagRepository.save(new Tag("tag1"));
        Tag tag2 = tagRepository.save(new Tag("tag2"));
        ContentsThread contentsThread = threadRepository.save(new ContentsThread("texts", profile, contents));
        threadTagRepository.save(new ThreadTag(contentsThread, tag1));

        // when
        UpdateThreadRequest request = new UpdateThreadRequest(JsonNullable.of("updateTexts"),
                JsonNullable.of(List.of(tag2.getId())),
                JsonNullable.of("updateImgUrl")
        );
        ContentsThread act = threadService.updateThread(contents.getId(), contentsThread.getId(), request);

        // then
        Assertions.assertThat(act.getTexts()).isEqualTo("updateTexts");
        Assertions.assertThat(act.getImgUrl()).isEqualTo("updateImgUrl");
        List<ThreadTag> actTags = threadTagRepository.findAllByContentsThreadId(act.getId());
        Assertions.assertThat(actTags.get(0).getTag().getId()).isEqualTo(tag2.getId());
    }
}