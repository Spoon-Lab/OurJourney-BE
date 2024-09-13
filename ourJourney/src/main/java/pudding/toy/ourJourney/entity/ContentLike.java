package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne @JoinColumn(name = "post_id")
    Contents contents;
    @ManyToOne @JoinColumn (name = "profile_id")
    Profile profile;
    public ContentLike(Contents contents, Profile profile){
        this.contents = contents;
        this.profile = profile;
    }

}
