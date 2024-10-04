package pudding.toy.ourJourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.entity.ContentsThread;
import pudding.toy.ourJourney.entity.ThreadTag;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThreadTagRepository extends JpaRepository<ThreadTag, Long> {
    List<ThreadTag> findAllByContentsThreadId(Long threadId);

    void deleteAllByContentsThreadId(Long threadId);

    Optional<List<ThreadTag>> findAllByContentsThread(ContentsThread contentsThread);
}
