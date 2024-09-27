package pudding.toy.ourJourney.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.ContentsThread;

import java.util.Optional;

@Repository
public interface ThreadRepository extends JpaRepository<ContentsThread, Long> {
    Page<ContentsThread> findByContentsAndDeletedAtIsNull(Pageable pageable, Contents contents);

    Optional<ContentsThread> findByIdAndDeletedAtIsNull(Long id);

    Long countByContents(Contents contents);
}
