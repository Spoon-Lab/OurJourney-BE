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
public class Post extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String imgUrl;
    @ManyToOne @JoinColumn(name = "category_id")
    Category category;
    @ManyToOne @JoinColumn(name = "profile_id")
    Profile profile;
    @OneToMany(mappedBy = "post")
    List<PostTag> postTags;
    @OneToMany(mappedBy= "post")
    List<PostLike> postLikes;
    @OneToMany(mappedBy = "post")
    List<Thread> threads;

}
