package pudding.toy.ourJourney.profile.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetMyCommentsDto {
    Long commendId;
    String texts;
    Long profileId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public GetMyCommentsDto(Long commendId, String texts, Long profileId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.commendId = commendId;
        this.texts = texts;
        this.profileId = profileId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
