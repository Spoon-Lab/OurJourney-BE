package pudding.toy.ourJourney.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne @JoinColumn(name = "follower_id")
    Profile follower;
    @ManyToOne @JoinColumn(name = "following_id")
    Profile following;
    public Follow(Profile follower, Profile following){
        this.follower = follower;
        this.following = following;
    }
    public Profile getFollower(){
        return this.follower;
    }
    public Profile getFollowing(){
        return this.following;
    }

}
