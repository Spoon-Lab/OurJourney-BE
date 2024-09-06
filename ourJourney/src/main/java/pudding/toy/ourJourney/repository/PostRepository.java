package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Contents;

@Repository
public interface PostRepository extends JpaRepository<Contents, Long>{
}
