package pudding.toy.ourJourney.mapper;

import io.swagger.v3.oas.annotations.security.OAuthFlow;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.dto.content.EditContentRequest;
import pudding.toy.ourJourney.entity.Category;
import pudding.toy.ourJourney.entity.Contents;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@Transactional
@SpringBootTest
public class ContentMapperTest {
    @Autowired
    ContentsMapper contentsMapper;
    Contents content;
    Category category;
    @BeforeEach
    void setUp(){
        category = new Category("name");
        content = new Contents("제목이었던거",category,null);
    }
    @Test
    void updateContentTest(){ //mapper 성공
        EditContentRequest ed = EditContentRequest.builder()
                .imgUrl("img.png")
                .build();
        contentsMapper.updateEntityFromDto(ed,content);
        Assertions.assertThat(content.getImgUrl()).isEqualTo("img.png");
        Assertions.assertThat(content.getContentTags()).isEmpty();
        Assertions.assertThat(content.getCategory().getName()).isEqualTo("name");
    }
}
