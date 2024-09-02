package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Post;
import pudding.toy.ourJourney.entity.Profile;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
}
