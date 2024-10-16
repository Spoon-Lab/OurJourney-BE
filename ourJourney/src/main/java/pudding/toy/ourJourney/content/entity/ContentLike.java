package pudding.toy.ourJourney.content.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;
import pudding.toy.ourJourney.global.entity.BaseTimeEntity;
import pudding.toy.ourJourney.profile.entity.Profile;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "deleted_at IS NULL")
public class ContentLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    Contents contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    Profile profile;

    public ContentLike(Contents contents, Profile profile) {
        this.contents = contents;
        this.profile = profile;
    }

}
