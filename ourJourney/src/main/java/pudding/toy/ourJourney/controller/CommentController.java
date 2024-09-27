package pudding.toy.ourJourney.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.dto.comment.*;
import pudding.toy.ourJourney.entity.Comment;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.CommentService;

@Tag(name = "Comment API")
@RestController
@RequestMapping("/contents/{contentsId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final AuthService authService;

    @Operation(summary = "댓글 생성")
    @PostMapping("")
    public CreateCommentResponse createComment(
            @PathVariable("contentsId") Long contentsId,
            @RequestBody @Valid CreateCommentRequest body
    ) {
        Comment comment = commentService.createComment(authService.getProfileWithAuthorize(), contentsId, body.getTexts());
        return new CreateCommentResponse(comment.getId());
    }

    // TODO: 2차 작업으로 미뤄짐
    @Operation(summary = "대댓글 생성")
    @PostMapping("/{commentId}")
    public CreateReCommentResponse createReComment(
            @PathVariable("contentsId") Long contentsId,
            @PathVariable("commentId") Long commentId,
            @RequestBody @Valid CreateReCommentRequest body
    ) {
        return new CreateReCommentResponse(1L);
    }

    @Operation(summary = "댓글 목록")
    @GetMapping("")
    public GetCommentResponse getComments(
            @PathVariable("contentsId") Long contentsId,
            @PageableDefault() Pageable pageable
    ) {
        return new GetCommentResponse(commentService.getComments(contentsId, pageable));
    }

    // TODO: login_required && is_owner
    @Operation(summary = "댓글 수정")
    @PatchMapping("/{commentId}")
    public void updateComment(
            @PathVariable("contentsId") Long contentsId,
            @PathVariable("commentId") Long commentId,
            @RequestBody @Valid UpdateCommentRequest body
    ) {
        authService.getProfileWithAuthorize();
        commentService.updateComment(contentsId, commentId, body.getTexts());
    }

    // TODO: login_required && is_owner
    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    public void deleteComment(
            @PathVariable("contentsId") Long contentsId,
            @PathVariable("commentId") Long commentId
    ) {
        authService.getProfileWithAuthorize();
        commentService.deleteComment(contentsId, commentId);
    }
}
