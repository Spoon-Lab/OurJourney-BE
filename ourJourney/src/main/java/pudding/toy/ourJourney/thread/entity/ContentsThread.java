package pudding.toy.ourJourney.thread.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.global.entity.BaseTimeEntity;
import pudding.toy.ourJourney.profile.entity.Profile;
import pudding.toy.ourJourney.tags.entity.Tag;
import pudding.toy.ourJourney.tags.entity.ThreadTag;

import java.util.ArrayList;
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

    @Column(name = "texts")
    String texts;

    @Column(name = "img_url")
    String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    Profile profile;

    @OneToMany(mappedBy = "contentsThread")
    List<ThreadTag> threadTags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    Contents contents;

    public ContentsThread(String texts, Profile profile, Contents contents) {
        this.texts = texts;
        this.profile = profile;
        this.contents = contents;
        this.threadTags = new ArrayList<>();
    }

    public List<String> getTagNames() {
        return this.getThreadTags().stream()
                .map(ThreadTag::getTag)
                .map(Tag::getName)
                .toList();
    }

    public void addTags(List<ThreadTag> tags) {
        threadTags.addAll(tags);
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
