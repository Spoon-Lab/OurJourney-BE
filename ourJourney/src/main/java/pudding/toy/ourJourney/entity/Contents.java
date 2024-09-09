package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contents extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String imgUrl;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;
    @OneToMany(mappedBy = "contents")
    List<ContentTag> contentTags;
    @OneToMany(mappedBy = "contents")
    List<ContentLike> contentLikes;
    @OneToMany(mappedBy = "contents")
    List<ContentsThread> contentsThreads;
    @Builder
    public Contents(String title,Category category,ContentTag contentTag){
        this.title = title;
        this.category = category;
        this.contentTags.add(contentTag);
    }

}
