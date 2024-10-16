package pudding.toy.ourJourney.comment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.global.entity.BaseTimeEntity;
import pudding.toy.ourJourney.profile.entity.Profile;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at is null")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    Contents contents;

    @Column(length = 200, nullable = false)
    String texts;


    public Comment(Profile profile, Contents contents, String texts) {
        this.profile = profile;
        this.contents = contents;
        this.texts = texts;
    }

    public Long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public Contents getContents() {
        return contents;
    }

    public String getTexts() {
        return texts;
    }

    public void update(String texts) {
        this.texts = texts;
    }
}
