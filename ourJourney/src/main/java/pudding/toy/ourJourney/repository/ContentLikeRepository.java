package pudding.toy.ourJourney.repository;

import com.querydsl.core.annotations.QueryEmbedded;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pudding.toy.ourJourney.entity.ContentLike;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.entity.Tag;

import javax.swing.text.AbstractDocument;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContentLikeRepository extends JpaRepository<ContentLike, Long>{

    @Query("SELECT cl.id FROM ContentLike cl WHERE cl.profile.id = :profileId")
    Page<Long> findAllByProfileId(@RequestParam("profileId") Long profileId, Pageable pageable);
    Boolean existsByContentsAndProfile(Contents contents, Profile profile);
    Optional<ContentLike> findByContentsAndProfile(Contents contents, Profile profile);
    Long countByContentsId(Long contentsId);
}
