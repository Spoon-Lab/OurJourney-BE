package pudding.toy.ourJourney.thread.dto;


import lombok.Data;
import pudding.toy.ourJourney.tags.dto.GetTagsDto;
import pudding.toy.ourJourney.tags.entity.ThreadTag;
import pudding.toy.ourJourney.thread.entity.ContentsThread;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
public class ThreadDetailResponse {
    private Long contentId;
    private Long threadId;
    private String texts;
    private String threadImg;
    private List<GetTagsDto> tagsDto;
    private ProfileThreadDto profileThreadDto;
    private boolean isEditable;
    private boolean isRemovable;


    public ThreadDetailResponse(Long contentId, ContentsThread thread, Optional<List<ThreadTag>> tags, boolean isEditable, boolean isRemovable) {
        this.contentId = contentId;
        this.threadId = thread.getId();
        this.texts = thread.getTexts();
        this.threadImg = thread.getImgUrl();
        this.tagsDto = getTagsDtos(tags);
        this.profileThreadDto = new ProfileThreadDto(thread.getProfile().getId(), thread.getProfile().getImgUrl(), thread.getProfile().getNickName());
        this.isEditable = isEditable;
        this.isRemovable = isRemovable;
    }

    private List<GetTagsDto> getTagsDtos(Optional<List<ThreadTag>> tags) {
        return tags
                .map(tagList -> tagList.stream()
                        .map(ThreadTag::getTag)
                        .map(tag -> new GetTagsDto(tag.getId(), tag.getName()))
                        .toList())
                .orElse(Collections.emptyList());
    }

}
