package pudding.toy.ourJourney.profile.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetMyContentsDto {
    Long contentId;
    String title;
    Long profileId;
    String contentImageUrl;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public GetMyContentsDto(Long contentId, String title, Long profileId, String contentImageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.contentId = contentId;
        this.title = title;
        this.profileId = profileId;
        this.contentImageUrl = contentImageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
