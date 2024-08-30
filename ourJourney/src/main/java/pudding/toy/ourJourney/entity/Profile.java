package pudding.toy.ourJourney.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Profile {
    @Id
    private Long id;
    private String nickName;
    private String profileImg;
    private String selfIntroduction;

}
