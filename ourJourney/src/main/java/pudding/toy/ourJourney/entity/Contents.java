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
    @Column(name = "title")
    String title;
    @Column(name = "img_url")
    String imgUrl;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;
    @OneToMany(mappedBy = "contents")
    List<ContentTag> contentTags;
    @OneToMany(mappedBy = "contents", cascade = CascadeType.REMOVE)
    List<ContentLike> contentLikes;
    @OneToMany(mappedBy = "contents")
    List<ContentsThread> contentsThreads;
    @OneToMany(mappedBy = "contents")
    List<Attendee> attendees;

    public Contents(Profile profile, Category category, String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.category = category;
        this.profile = profile;
    }

    @Builder
    public Contents(String title, Category category, ContentTag contentTag, Profile profile) {
        this.title = title;
        this.category = category;
        this.contentTags = new ArrayList<>();
        this.contentLikes = new ArrayList<>();
        this.profile = profile;
        this.contentsThreads = new ArrayList<>();
        this.addTag(contentTag);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void addTag(ContentTag contentTag) {
        if (contentTag != null) {
            this.contentTags.add(contentTag);
        }
    }

    public void setTags(List<ContentTag> contentTags) {
        for (ContentTag tags : contentTags) {
            this.addTag(tags);
        }
    }

    public void addAttendee(Attendee attendee) {
        if (attendee != null) {
            this.attendees.add(attendee);
        }
    }

    public void addAttendees(List<Attendee> attendees) {
        for (Attendee attendee : attendees) {
            this.addAttendee(attendee);
        }
    }

}
