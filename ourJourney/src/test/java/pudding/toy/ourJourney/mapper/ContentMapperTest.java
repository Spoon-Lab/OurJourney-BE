package pudding.toy.ourJourney.mapper;

import io.swagger.v3.oas.annotations.security.OAuthFlow;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.config.ProfileInitializer;
import pudding.toy.ourJourney.dto.content.CreateContentRequest;
import pudding.toy.ourJourney.dto.content.EditContentRequest;
import pudding.toy.ourJourney.entity.Category;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.service.ContentService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@Transactional
@SpringBootTest
public class ContentMapperTest {
    @Autowired
    EditContentsMapper contentsMapper;
    Contents content;
    Category category;
    ContentService contentService;
    ProfileInitializer profileInitializer;
    @BeforeEach
    void setUp(){
        category = new Category("name");
        content = new Contents("제목이었던거",category,null,profileInitializer.dummyProfile);
    }
    @Test
    void updateContentTest(){ //mapper 성공
        EditContentRequest ed = EditContentRequest.builder()
                .imgUrl(JsonNullable.of("img.png"))
                .build();
        contentsMapper.updateEntityFromDto(ed,content);
        Assertions.assertThat(content.getImgUrl()).isEqualTo("img.png");
        Assertions.assertThat(content.getContentTags()).isEmpty();
        Assertions.assertThat(content.getCategory().getName()).isEqualTo("name");
    }
    @Test
    void contentTest(){

    }
}

