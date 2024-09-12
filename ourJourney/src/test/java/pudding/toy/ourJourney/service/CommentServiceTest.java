package pudding.toy.ourJourney.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pudding.toy.ourJourney.dto.comment.GetCommentsDto;
import pudding.toy.ourJourney.entity.Comment;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.CommentRepository;
import pudding.toy.ourJourney.repository.ContentRepository;
import pudding.toy.ourJourney.repository.ProfileRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Transactional
@SpringBootTest
class CommentServiceTest {
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CommentService commentService;

    @Test
    void createCommentTest() {
        // given
        Profile profile = profileRepository.save(new Profile(2L, "nickName", null, "selfIntroduce"));
        Contents contents = contentRepository.save(new Contents(profile, null, "title", null));

        // when
        Comment act = commentService.createComment(profile, contents.getId(), "texts");

        // then
        Assertions.assertThat(act.getId()).isNotNull();
    }

    @Test
    void getCommentsTest() {
        // given
        Profile profile = profileRepository.save(new Profile(2L, "nickName", null, "selfIntroduce"));
        Contents contents = contentRepository.save(new Contents(profile, null, "title", null));

        List<Comment> comments = IntStream.range(0, 11)
                .mapToObj(i -> new Comment(profile, contents, "texts" + i)).toList();
        commentRepository.saveAll(comments);

        // when
        PageRequest pageRequest = PageRequest.of(0, 10);
        PageImpl<GetCommentsDto> act = commentService.getComments(contents.getId(), pageRequest);

        // then
        Assertions.assertThat(act.getContent().size()).isEqualTo(10);
    }
}