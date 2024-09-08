package pudding.toy.ourJourney.dto.profile;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetMyCommentsDto {
    Long commendId;
    String content;
    Long profileId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public GetMyCommentsDto(Long commendId, String content, Long profileId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.commendId = commendId;
        this.content = content;
        this.profileId = profileId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
