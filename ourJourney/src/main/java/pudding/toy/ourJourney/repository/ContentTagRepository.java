package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.ContentTag;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Tag;

import java.util.Optional;

@Repository
public interface ContentTagRepository extends JpaRepository<ContentTag, Long>{
    Optional<ContentTag> findByContents(Contents contents);
}
