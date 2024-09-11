package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.dto.content.CreateContentRequest;
import pudding.toy.ourJourney.dto.content.DetailContentResponse;
import pudding.toy.ourJourney.dto.content.EditContentRequest;
import pudding.toy.ourJourney.dto.content.ListContentDto;
import pudding.toy.ourJourney.entity.Category;
import pudding.toy.ourJourney.entity.ContentTag;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Tag;
import pudding.toy.ourJourney.mapper.EditContentsMapper;
import pudding.toy.ourJourney.repository.CategoryRepository;
import pudding.toy.ourJourney.repository.ContentRepository;
import pudding.toy.ourJourney.repository.ContentTagRepository;
import pudding.toy.ourJourney.repository.TagRepository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {
    private final TagRepository tagRepository;
    private final ContentTagRepository contentTagRepository;

    public void createNewTags(String tagName){
        tagRepository.save(new Tag(tagName));
    }
    public Long getTags(String tagName){
        Tag tag = tagRepository.findByName(tagName).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return tag.getId();
    }
    public void addContentTag(List<Long> tagIds,Contents content){
        List<Tag> tags = tagRepository.findAllById(tagIds);
        if(tagIds.size() != tags.size()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        List<ContentTag> contentTags = tags.stream()
                .map(tag -> new ContentTag(content,tag))
                .toList();
        contentTagRepository.saveAll(contentTags);
    }


}
