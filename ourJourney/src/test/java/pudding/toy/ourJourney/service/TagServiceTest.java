package pudding.toy.ourJourney.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pudding.toy.ourJourney.dto.tags.GetTagsDto;
import pudding.toy.ourJourney.entity.Tag;
import pudding.toy.ourJourney.repository.TagRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TagServiceTest {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;


    @Test
    void getTags() {
        tagRepository.save(new Tag("tagName"));
        tagRepository.save(new Tag("tagName2"));
        tagRepository.save(new Tag("another tagName"));

        PageRequest pageRequest = PageRequest.of(0, 10);
        PageImpl<GetTagsDto> act = tagService.getTags("tagName", pageRequest);

        Assertions.assertThat(act.getTotalElements()).isEqualTo(2);
    }
}