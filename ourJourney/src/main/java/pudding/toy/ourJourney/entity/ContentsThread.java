package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "thread")
public class ContentsThread extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String texts;
    String imgUrl;
    @ManyToOne @JoinColumn(name = "profile_id")
    Profile profile;
    @OneToMany @JoinColumn(name = "tag_id")
    List<ThreadTag> threadTags;
    @ManyToOne @JoinColumn(name = "post_id")
    Contents contents;
    public ContentsThread(String texts,Contents contents){
        this.texts = texts;
        this.contents = contents;
    }

}
