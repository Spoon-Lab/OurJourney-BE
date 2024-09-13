package pudding.toy.ourJourney.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    Page<Tag> findAllByNameLike(String name, Pageable pageable);
    Long countByName(String name);
}
