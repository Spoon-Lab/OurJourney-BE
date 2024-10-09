package pudding.toy.ourJourney.mapper;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pudding.toy.ourJourney.category.entity.Category;
import pudding.toy.ourJourney.content.dto.CreateContentRequest;
import pudding.toy.ourJourney.content.entity.Contents;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class JacksonConfigTest {

    @Autowired
    private ObjectMapper mapper;

    @Test
    void should_update_all_entities_in_product_except_id() throws JsonMappingException {
        CreateContentRequest update1 = new CreateContentRequest(
                "title1",
                2L,
                null,  // imgUrl이 null로 설정됨
                Optional.of(List.of(3L, 2L, 5L)),
                Optional.of(List.of(3L, 4L))
        );

        CreateContentRequest update2 = new CreateContentRequest(
                "title2",
                3L,
                Optional.of("img2.png"),  // imgUrl이 "img2.png"로 설정됨
                Optional.of(List.of(33L, 222L, 533L)),
                Optional.of(List.of(3L, 2L, 5L))
        );

        Category category = new Category("name");

        Contents content1 = new Contents("title1", category, null, null);
        content1.setImgUrl("img.png");  // imgUrl 초기값

        Contents content2 = new Contents("title2", category, null, null);

        mapper.updateValue(update1, content1);  // update1 적용 (imgUrl을 null로 설정)
        mapper.updateValue(update2, content2);  // update2 적용 (imgUrl을 "img2.png"로 설정)

        // 업데이트 결과 검증
        Assertions.assertNull(content1.getImgUrl());  // content1의 imgUrl은 null이어야 함
        Assertions.assertEquals("img2.png", content2.getImgUrl());  // content2의 imgUrl은 "img2.png"여야 함
    }


}
