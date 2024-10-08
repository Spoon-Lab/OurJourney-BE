package pudding.toy.ourJourney.tags.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.tags.dto.GetTagsDto;
import pudding.toy.ourJourney.tags.entity.Tag;
import pudding.toy.ourJourney.tags.repository.ContentTagRepository;
import pudding.toy.ourJourney.tags.repository.TagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {
    private final TagRepository tagRepository;
    private final ContentTagRepository contentTagRepository;

    public Long createNewTags(String tagName) {
        Tag tag = new Tag(tagName);
        tagRepository.save(tag);
        return tag.getId();
    }

    public PageImpl<GetTagsDto> getTags(String tagName, Pageable pageable) {
        String searchKeyWord = tagName + "%";
        Page<Tag> list = tagRepository.findAllByNameLike(searchKeyWord, pageable);
        Long totalCount = tagRepository.countByName(searchKeyWord);

        List<GetTagsDto> tags = list.getContent().stream().map(
                tag -> new GetTagsDto(tag.getId(), tag.getName())
        ).toList();

        return new PageImpl<>(tags, pageable, totalCount);
    }

}
