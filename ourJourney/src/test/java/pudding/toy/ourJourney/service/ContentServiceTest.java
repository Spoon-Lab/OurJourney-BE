package pudding.toy.ourJourney.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.category.entity.Category;
import pudding.toy.ourJourney.category.repository.CategoryRepository;
import pudding.toy.ourJourney.comment.CommentRepository;
import pudding.toy.ourJourney.comment.entity.Comment;
import pudding.toy.ourJourney.content.dto.DetailContentResponse;
import pudding.toy.ourJourney.content.entity.ContentLike;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.content.repository.ContentLikeRepository;
import pudding.toy.ourJourney.content.repository.ContentRepository;
import pudding.toy.ourJourney.content.service.ContentService;
import pudding.toy.ourJourney.profile.entity.Profile;
import pudding.toy.ourJourney.profile.repository.ProfileRepository;

import java.util.Optional;

@SpringBootTest
@Transactional
public class ContentServiceTest {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ContentLikeRepository contentLikeRepository;

    @Autowired
    private ContentService contentService;

    @Test
    void getDetailContentTest() {
        // given
        Profile profile = profileRepository.save(new Profile(2L, "nickname", "imgUrl", "selfIntroduction"));
        Category category = categoryRepository.save(new Category("category"));
        Contents contents = contentRepository.save(new Contents("title", category, null, profile));
        commentRepository.save(new Comment(profile, contents, "comment"));
        commentRepository.save(new Comment(profile, contents, "comment2"));
        contentLikeRepository.save(new ContentLike(contents, profile));

        // when
        DetailContentResponse act = contentService.getDetailContent(contents.getId(), Optional.of(profile));

        // then
        Assertions.assertThat(act.getTitle()).isEqualTo(contents.getTitle());
        Assertions.assertThat(act.getCommentCount()).isEqualTo(2);
        Assertions.assertThat(act.getLikeCount()).isEqualTo(1);
    }
}
