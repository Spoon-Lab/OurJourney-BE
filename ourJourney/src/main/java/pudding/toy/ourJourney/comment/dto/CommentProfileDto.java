package pudding.toy.ourJourney.comment.dto;

import lombok.Data;

@Data
public class CommentProfileDto {
    private Long profileId;
    private String profileImgUrl;
    private String nickName;

    public CommentProfileDto(Long profileId, String profileImgUrl, String nickName) {
        this.profileId = profileId;
        this.profileImgUrl = profileImgUrl;
        this.nickName = nickName;
    }
}


