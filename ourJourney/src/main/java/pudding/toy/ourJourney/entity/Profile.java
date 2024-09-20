package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Profile extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    Long userId;

    @Column(name = "nick_name", length = 32, unique = true)
    String nickName;

    @Column(name = "img_url")
    String imgUrl;

    @Column(name = "self_introduction")
    String selfIntroduction;
    @OneToMany(mappedBy = "profile")
    List<Contents> contents;
    @OneToMany(mappedBy = "follower")
    List<Follow> follows;

    public void defaultName(String nickName){
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getProfileImg() {
        return imgUrl;
    }
    public String getSelfIntroduction() {
        return selfIntroduction;
    }
    @Builder
    public Profile(Long userId) {
        this.userId = userId;
    }
    static final List<String> adjectives = Arrays.asList("예쁜", "졸린", "작은", "큰", "빠른", "따뜻한", "밝은", "산뜻한", "사랑스러운", "행복한", "귀여운");
    public String createRandomNickName(){
        int randomIndex = (int)(Math.random()*adjectives.size());
        return adjectives.get(randomIndex)+"푸딩"+(int)(Math.random()*10000);
    }
    public int followingNum(){
        return (int) follows.stream()
                .filter(follow -> follow.getFollower().equals(this))
                .count();
    }
    public int followerNum(){
        return (int) follows.stream()
                .filter(follow -> follow.getFollowing().equals(this))
                .count();
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setProfileImg(String profileImg) {
        this.imgUrl = profileImg;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public void setContents(List<Contents> contents) {
        this.contents = contents;
    }

    public void setFollows(List<Follow> follows) {
        this.follows = follows;
    }
}
