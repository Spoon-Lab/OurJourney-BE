package pudding.toy.ourJourney.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.v3.oas.models.media.Content;
import pudding.toy.ourJourney.dto.content.DetailContentResponse;
import pudding.toy.ourJourney.entity.*;
import pudding.toy.ourJourney.repository.*;

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
        DetailContentResponse act = contentService.getDetailContent(contents.getId());

        // then
        Assertions.assertThat(act.getTitle()).isEqualTo(contents.getTitle());
//        Assertions.assertThat(act.getCommentCount()).isEqualTo(2);
//        Assertions.assertThat(act.getLikeCount()).isEqualTo(1);
    }
}
