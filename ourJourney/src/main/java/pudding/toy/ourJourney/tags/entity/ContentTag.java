package pudding.toy.ourJourney.tags.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import pudding.toy.ourJourney.content.entity.Contents;

@Entity
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    Contents contents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    Tag tag;

    public ContentTag(Contents contents, Tag tag) {
        this.contents = contents;
        this.tag = tag;
    }

}
