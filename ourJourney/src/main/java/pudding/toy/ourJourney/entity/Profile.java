package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Column(name = "nick_name", length = 32)
    String nickName;

    @Column(name = "profile_img")
    String profileImg;

    @Column(name = "self_introduction")
    String selfIntroduction;

    @OneToMany(mappedBy = "profile")
    List<Contents> contents;

    public String getNickName() {
        return nickName;
    }

    public String getProfileImg() {
        return profileImg;
    }
    public String getSelfIntroduction() {
        return selfIntroduction;
    }
    @Builder
    public Profile(Long userId) {
        this.userId = userId;
        this.nickName = this.createRandomNickName();
    }
    public String createRandomNickName(){
        final List<String> adjectives = Arrays.asList("예쁜", "졸린", "작은", "큰", "빠른", "따뜻한", "밝은", "산뜻한", "사랑스러운", "행복한", "귀여운");
        int randomIndex = (int)(Math.random()*adjectives.size());
        return adjectives.get(randomIndex)+"푸딩"+(int)(Math.random()*1000);
    }
}
