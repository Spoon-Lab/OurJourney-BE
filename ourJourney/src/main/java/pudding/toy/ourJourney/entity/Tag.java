package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tag{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name; //해시태그 이름
    @OneToMany(mappedBy = "tag")
    List<PostLike> postLikes;

}
