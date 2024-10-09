package pudding.toy.ourJourney.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.category.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
