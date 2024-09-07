package pudding.toy.ourJourney.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pudding.toy.ourJourney.dto.comment.CreateCommentRequest;
import pudding.toy.ourJourney.dto.comment.CreateCommentResponse;
import pudding.toy.ourJourney.dto.comment.CreateReCommentRequest;
import pudding.toy.ourJourney.dto.comment.CreateReCommentResponse;

@RestController
@RequiredArgsConstructor
public class CommentController {

    @Operation(summary = "댓글 생성")
    @PostMapping("/contents/{contentsId}/comments/")
    public CreateCommentResponse createComment(
            @PathVariable("contentsId") Long contentsId,
            @RequestBody @Valid CreateCommentRequest body
    ) {
        return new CreateCommentResponse(1L);
    }

    @Operation(summary = "대댓글 생성")
    @PostMapping("/contents/{contentsId}/comments/{commentId}/")
    public CreateReCommentResponse createReComment(
            @PathVariable("contentsId") Long contentsId,
            @PathVariable("commentId") Long commentId,
            @RequestBody @Valid CreateReCommentRequest body
    ) {
        return new CreateReCommentResponse(1L);
    }
}
