package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;

    @ManyToOne
    @JoinColumn(name = "contents_id")
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
