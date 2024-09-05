package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Category;
import pudding.toy.ourJourney.entity.ThreadTag;

@Repository
public interface ThreadTagRepository extends JpaRepository<ThreadTag, Long>{
}
