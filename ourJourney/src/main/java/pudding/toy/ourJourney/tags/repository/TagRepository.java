package pudding.toy.ourJourney.tags.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.tags.entity.Tag;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Page<Tag> findAllByNameLike(String name, Pageable pageable);

    Long countByName(String name);

    Optional<List<Tag>> findAllById(Contents contents);
}
