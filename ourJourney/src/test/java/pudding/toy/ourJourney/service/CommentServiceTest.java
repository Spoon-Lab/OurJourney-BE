package pudding.toy.ourJourney.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pudding.toy.ourJourney.comment.CommentRepository;
import pudding.toy.ourJourney.comment.dto.GetCommentsDto;
import pudding.toy.ourJourney.comment.entity.Comment;
import pudding.toy.ourJourney.comment.service.CommentService;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.content.repository.ContentRepository;
import pudding.toy.ourJourney.profile.entity.Profile;
import pudding.toy.ourJourney.profile.repository.ProfileRepository;

import java.util.List;
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
        Profile profile = profileRepository.save(new Profile(2L));
        Contents contents = contentRepository.save(new Contents(profile, null, "title", null));

        // when
        Comment act = commentService.createComment(profile, contents.getId(), "texts");

        // then
        Assertions.assertThat(act.getId()).isNotNull();
    }

    @Test
    void getCommentsTest() {
        // given
        Profile profile = profileRepository.save(new Profile(2L));
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

    @Test
    void updateCommentTest() {
        // given
        Profile profile = profileRepository.save(new Profile(2L));
        Contents contents = contentRepository.save(new Contents(profile, null, "title", null));
        Comment comment = commentRepository.save(new Comment(profile, contents, "texts"));

        // when
        commentService.updateComment(contents.getId(), comment.getId(), "updateTexts");

        // then
        Comment act = commentRepository.findById(comment.getId()).orElse(null);
        Assertions.assertThat(act).isNotNull();
        Assertions.assertThat(act.getTexts()).isEqualTo("updateTexts");
    }

    @Test
    void deleteCommentTest() {
        // given
        Profile profile = profileRepository.save(new Profile(2L));
        Contents contents = contentRepository.save(new Contents(profile, null, "title", null));
        Comment comment = commentRepository.save(new Comment(profile, contents, "texts"));

        // when
        commentService.deleteComment(contents.getId(), comment.getId());

        // then
        Comment act = commentRepository.findByIdAndDeletedAtIsNull(comment.getId()).orElse(null);
        Assertions.assertThat(act).isNull();
    }
}