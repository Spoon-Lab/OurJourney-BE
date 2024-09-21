package pudding.toy.ourJourney.mapper;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.config.DummyDataInitializer;
import pudding.toy.ourJourney.dto.content.UpdateContentRequest;
import pudding.toy.ourJourney.entity.Category;
import pudding.toy.ourJourney.entity.Contents;

@Transactional
@SpringBootTest
public class ContentMapperTest {
    @Autowired
    UpdateContentsMapper contentsMapper;
    Contents content;
    Category category;
    @Autowired
    DummyDataInitializer dummyDataInitializer;

    @BeforeEach
    void setUp() {
        category = new Category("name");
        content = new Contents("제목이었던거", category, null, dummyDataInitializer.dummyProfile);
    }

    @Test
    void updateContentTest() { //mapper 성공
        UpdateContentRequest ed = UpdateContentRequest.builder()
                .imgUrl(JsonNullable.of("img.png"))
                .build();
        contentsMapper.updateEntityFromDto(ed, content);
        Assertions.assertThat(content.getImgUrl()).isEqualTo("img.png");
        Assertions.assertThat(content.getContentTags()).isEmpty();
        Assertions.assertThat(content.getCategory().getName()).isEqualTo("name");
    }

    @Test
    void contentTest() {

    }
}
