package pudding.toy.ourJourney.content.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.content.entity.Contents;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Contents, Long> {
    Optional<Contents> findByIdAndDeletedAtIsNull(Long id);

    Page<Contents> findAllByProfileId(Long profileId, Pageable pageable);

    List<Contents> findAllByIdIn(List<Long> contentId);
}
