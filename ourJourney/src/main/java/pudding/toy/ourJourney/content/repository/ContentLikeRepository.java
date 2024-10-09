package pudding.toy.ourJourney.content.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.content.entity.ContentLike;
import pudding.toy.ourJourney.content.entity.Contents;
import pudding.toy.ourJourney.profile.entity.Profile;

import java.util.Optional;

@Repository
public interface ContentLikeRepository extends JpaRepository<ContentLike, Long> {

    //    @Query("SELECT cl.id FROM ContentLike cl WHERE cl.profile.id = :profileId")
//    Page<Long> findAllByProfileId(@Param("profileId") Long profileId, Pageable pageable);
    Page<ContentLike> findAllByProfileId(Long profileId, Pageable pageable);

    Boolean existsByContentsAndProfile(Contents contents, Profile profile);

    Optional<ContentLike> findByContentsAndProfile(Contents contents, Profile profile);

    Long countByContentsId(Long contentsId);
}
