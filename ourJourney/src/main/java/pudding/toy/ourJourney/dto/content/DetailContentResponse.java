package pudding.toy.ourJourney.dto.content;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import pudding.toy.ourJourney.entity.Contents;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DetailContentResponse {
    Long contentId;
    String title;
    String postImg;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Long likeCount;
    Long commentCount;
    Boolean isEditable;
    Boolean isRemovable;

    public static DetailContentResponse from(Contents contents, Long likeCount, Long commentCount, Boolean isEditable, Boolean isRemovable) {
        return new DetailContentResponse(
                contents.getId(),
                contents.getTitle(),
                contents.getImgUrl(),
                contents.getCreatedAt(),
                contents.getUpdatedAt(),
                likeCount,
                commentCount,
                isEditable,
                isRemovable
        );
    }
}
