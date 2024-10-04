package pudding.toy.ourJourney.dto.thread;


import lombok.Data;
import pudding.toy.ourJourney.dto.tags.GetTagsDto;
import pudding.toy.ourJourney.entity.ContentsThread;
import pudding.toy.ourJourney.entity.ThreadTag;

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
