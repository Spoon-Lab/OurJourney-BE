package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Post;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long>{
}
