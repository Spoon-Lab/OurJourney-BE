package pudding.toy.ourJourney.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.entity.Comment;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.ContentRepository;


@Transactional
@SpringBootTest
class CommentServiceTest {
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CommentService commentService;

    @Test
    void createCommentTest() {
        // given
        Profile profile = new Profile(1L, "nickName", null, "selfIntroduce");
        Contents contents = new Contents(profile, null, "title", null);
        contentRepository.save(contents);

        // when
        Comment act = commentService.createComment(profile, contents.getId(), "texts");

        // then
        Assertions.assertThat(act.getId()).isNotNull();
    }
}