package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity @Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentTag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne @JoinColumn(name = "contents_id")
    Contents contents;
    @ManyToOne @JoinColumn(name = "tag_id")
    Tag tag;
    public ContentTag(Contents contents,Tag tag){
        this.contents = contents;
        this.tag = tag;
    }

}
