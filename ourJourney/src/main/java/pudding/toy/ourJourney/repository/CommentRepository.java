package pudding.toy.ourJourney.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Comment;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndDeletedAtIsNull(Long id);

    Page<Comment> findAllByContentsIdAndDeletedAtIsNull(Long contentsId, Pageable pageable);

    Long countByContentsIdAndDeletedAtIsNull(Long contentsId);
    Page<Comment> findAllByProfileId(Long profileId,Pageable pageable);
}
