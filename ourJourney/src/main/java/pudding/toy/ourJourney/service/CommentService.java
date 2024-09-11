package pudding.toy.ourJourney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pudding.toy.ourJourney.dto.comment.CommentProfileDto;
import pudding.toy.ourJourney.dto.comment.GetCommentsDto;
import pudding.toy.ourJourney.entity.Comment;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.repository.CommentRepository;
import pudding.toy.ourJourney.repository.ContentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;

    public Comment createComment(Profile profile, Long contentsId, String texts) {
        Contents contents = findContents(contentsId);

        Comment comment = new Comment(profile, contents, texts);
        return commentRepository.save(comment);
    }

    public PageImpl<GetCommentsDto> getComments(Long contentsId, Pageable pageable) {
        Contents contents = findContents(contentsId);
        Page<Comment> comments = commentRepository.findAllByContentsIdAndDeletedAtIsNull(contents.getId(), pageable);
        List<GetCommentsDto> list = comments.getContent().stream().map(
                comment -> new GetCommentsDto(
                        comment.getId(),
                        comment.getTexts(),
                        new CommentProfileDto(
                                comment.getProfile().getId(),
                                comment.getProfile().getProfileImg(),
                                comment.getProfile().getNickName()
                        ),
                        comment.getCreatedAt()
                )
        ).toList();
        Long totalCount = commentRepository.countByContentsIdAndDeletedAtIsNull(contents.getId());

        return new PageImpl<>(list, pageable, totalCount);
    }

    private Contents findContents(Long contentsId) {
        return contentRepository.findById(contentsId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 컨텐츠입니다.")
        );
    }
}
