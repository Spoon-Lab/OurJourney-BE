package pudding.toy.ourJourney.dto.profile;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetMyContentsDto {
    Long contentId;
    String title;
    String contentImageUrl;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public GetMyContentsDto(Long contentId, String title, String contentImageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.contentId = contentId;
        this.title = title;
        this.contentImageUrl = contentImageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
