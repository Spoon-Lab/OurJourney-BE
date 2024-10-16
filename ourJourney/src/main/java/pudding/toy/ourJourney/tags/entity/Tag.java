package pudding.toy.ourJourney.tags.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import pudding.toy.ourJourney.global.entity.BaseTimeEntity;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String name; //해시태그 이름
    @OneToMany(mappedBy = "tag")
    List<ContentTag> contentTags;

    public Tag(String name) {
        this.name = name;
    }

}
