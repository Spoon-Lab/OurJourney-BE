package pudding.toy.ourJourney.content.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import pudding.toy.ourJourney.content.entity.Contents;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ListContentDto {
    Long contentId;
    String title;
    String postImg;
    ContentProfileDto contentProfileDto;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public ListContentDto(Contents contents) {
        this.contentId = contents.getId();
        this.title = contents.getTitle();
        this.postImg = contents.getImgUrl();
        this.contentProfileDto = new ContentProfileDto(
                contents.getProfile().getId(),
                contents.getProfile().getProfileImg(),
                contents.getProfile().getNickName()
        );
        this.createdAt = contents.getCreatedAt();
        this.updatedAt = contents.getUpdatedAt();
    }

}
