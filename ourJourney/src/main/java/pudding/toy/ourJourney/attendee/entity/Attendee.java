package pudding.toy.ourJourney.attendee.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.profile.entity.Profile;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    Profile profile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    Contents contents;

    public Attendee(Profile profile, Contents contents) {
        this.profile = profile;
        this.contents = contents;
    }
}
