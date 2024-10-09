package pudding.toy.ourJourney.thread.dto;

import lombok.Data;

@Data
public class ProfileThreadDto {
    private Long profileId;
    private String imgUrl;
    private String nickName;

    public ProfileThreadDto(Long id, String imgUrl, String nickName) {
        this.profileId = id;
        this.imgUrl = imgUrl;
        this.nickName = nickName;
    }

}
