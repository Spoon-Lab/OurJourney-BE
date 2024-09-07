package pudding.toy.ourJourney.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetCommentsDto {
    private Long commendId;
    private String content;
    private CommentProfileDto commentProfileDto;
    private LocalDateTime createdAt;

    public GetCommentsDto(Long commendId, String content, CommentProfileDto commentProfileDto, LocalDateTime createdAt) {
        this.commendId = commendId;
        this.content = content;
        this.commentProfileDto = commentProfileDto;
        this.createdAt = createdAt;
    }
}

