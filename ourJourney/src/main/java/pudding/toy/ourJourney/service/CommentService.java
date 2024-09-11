package pudding.toy.ourJourney.service;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pudding.toy.ourJourney.entity.Comment;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.CommentRepository;
import pudding.toy.ourJourney.repository.ContentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;

    public Comment createComment(Profile profile, Long contentsId, String texts) {
        Contents contents = contentRepository.findById(contentsId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 컨텐츠입니다.")
        );

        Comment comment = new Comment(profile, contents, texts);
        return commentRepository.save(comment);
    }
}
