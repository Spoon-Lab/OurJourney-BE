package pudding.toy.ourJourney.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByContentsIdAndDeletedAtIsNull(Long contentsId, Pageable pageable);

    Long countByContentsIdAndDeletedAtIsNull(Long contentsId);
}
