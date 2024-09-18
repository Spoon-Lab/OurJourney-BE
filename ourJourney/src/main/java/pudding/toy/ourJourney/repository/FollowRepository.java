package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Follow;
import pudding.toy.ourJourney.entity.Profile;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>{
    Boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
    Optional<Follow> findByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
