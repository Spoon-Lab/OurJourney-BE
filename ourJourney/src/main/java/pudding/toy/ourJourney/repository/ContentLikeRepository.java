package pudding.toy.ourJourney.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import pudding.toy.ourJourney.entity.ContentLike;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;

@Repository
public interface ContentLikeRepository extends JpaRepository<ContentLike, Long>{

    @Query("SELECT cl.id FROM ContentLike cl WHERE cl.profile.id = :profileId")
    Page<Long> findAllByProfileId(@RequestParam("profileId") Long profileId, Pageable pageable);
    
    Boolean existsByContentsAndProfile(Contents contents, Profile profile);

    Optional<ContentLike> findByContentsAndProfile(Contents contents, Profile profile);

    Long countByContentsId(Long contentsId);
}
