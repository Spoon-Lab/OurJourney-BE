package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "deleted_at IS NULL")
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
    public Contents(String title, Category category, ContentTag contentTag) {
        this.title = title;
        this.category = category;
        this.contentTags  = new ArrayList<>();
        this.addTag(contentTag);
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public void addTag(ContentTag contentTag){
        if(contentTag != null){
            this.contentTags.add(contentTag);
        }
    }
    public void addTags(List<ContentTag> contentTags){
        for(ContentTag tags: contentTags){
            this.addTag(tags);
        }
    }

}
