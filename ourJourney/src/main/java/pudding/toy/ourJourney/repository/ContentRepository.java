package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Contents;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Contents, Long>{
    Optional<Contents> findByIdAndDeletedAtIsNull(Long id);
}
