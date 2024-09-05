package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Attendee;
import pudding.toy.ourJourney.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
