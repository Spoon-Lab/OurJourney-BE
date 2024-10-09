package pudding.toy.ourJourney.tags.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.tags.entity.ThreadTag;
import pudding.toy.ourJourney.thread.entity.ContentsThread;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThreadTagRepository extends JpaRepository<ThreadTag, Long> {
    List<ThreadTag> findAllByContentsThreadId(Long threadId);

    void deleteAllByContentsThreadId(Long threadId);

    Optional<List<ThreadTag>> findAllByContentsThread(ContentsThread contentsThread);
}
