package pudding.toy.ourJourney.dto.thread;

public class ThreadProfileDto {
    private Long profileId;
    private String imgUrl;
    private String nickName;
    public ThreadProfileDto(Long id, String imgUrl, String nickName){
        this.profileId =id;
        this.imgUrl = imgUrl;
        this.nickName = nickName;
    }

}
