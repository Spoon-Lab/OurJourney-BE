package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Profile {
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

    public Optional<String> getNickName() {
        return Optional.ofNullable(nickName);
    }

    public Optional<String> getProfileImg() {
        return Optional.ofNullable(profileImg);
    }

    public Optional<String> getSelfIntroduction() {
        return Optional.ofNullable(selfIntroduction);
    }
}
