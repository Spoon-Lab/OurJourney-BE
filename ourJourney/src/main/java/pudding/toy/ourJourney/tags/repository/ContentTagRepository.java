package pudding.toy.ourJourney.tags.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.tags.entity.ContentTag;
import pudding.toy.ourJourney.tags.entity.Tag;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentTagRepository extends JpaRepository<ContentTag, Long> {
    Optional<List<ContentTag>> findAllByContents(Contents contents);

    Optional<ContentTag> findByContents(Contents contents);

    boolean existsByContentsAndTag(Contents contents, Tag Tag);
}
