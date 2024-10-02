package pudding.toy.ourJourney.dto.content;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import pudding.toy.ourJourney.dto.tags.GetTagsDto;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.entity.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DetailContentResponse {
    Long contentId;
    String title;
    String postImg;
    List<GetTagsDto> tags;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    ContentProfileDto contentProfileDto;
    Long likeCount;
    Long commentCount;
    Boolean isLiked;
    Boolean isEditable;
    Boolean isRemovable;

    public static DetailContentResponse from(Contents contents, Long likeCount, List<Tag> tags, Long commentCount, Boolean isLiked, Boolean isEditable, Boolean isRemovable) {
        return new DetailContentResponse(
                contents.getId(),
                contents.getTitle(),
                contents.getImgUrl(),
                getTagDto(tags),
                contents.getCreatedAt(),
                contents.getUpdatedAt(),
                contentProfileDto(contents.getProfile()),
                likeCount,
                commentCount,
                isLiked,
                isEditable,
                isRemovable
        );
    }

    private static List<GetTagsDto> getTagDto(List<Tag> tags) {
        return tags.stream()
                .map(tag -> new GetTagsDto(tag.getId(), tag.getName()))
                .toList();
    }

    private static ContentProfileDto contentProfileDto(Profile profile) {
        return new ContentProfileDto(profile.getId(), profile.getProfileImg(), profile.getNickName());
    }

}
