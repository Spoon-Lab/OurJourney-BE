package pudding.toy.ourJourney.dto.profile;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetLikesContentsDto {
    Long contentId;
    String title;
    Long profileId;
    String postImageUrl;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public GetLikesContentsDto(Long contentId, String title, Long profileId, String postImageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.contentId = contentId;
        this.title = title;
        this.profileId = profileId;
        this.postImageUrl = postImageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
