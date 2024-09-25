package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "thread")
public class ContentsThread extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "text")
    String texts;
    @Column(name = "img_url")
    String imgUrl;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;

    @OneToMany
    @JoinColumn(name = "tag_id")
    List<ThreadTag> threadTags;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Contents contents;

    public ContentsThread(String texts, String imgUrl, Profile profile, Contents contents) {
        this.texts = texts;
        this.imgUrl = imgUrl;
        this.profile = profile;
        this.contents = contents;
    }

    public List<String> getTagNames() {
        return this.getThreadTags().stream()
                .map(ThreadTag::getTag)
                .map(Tag::getName)
                .toList();
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
