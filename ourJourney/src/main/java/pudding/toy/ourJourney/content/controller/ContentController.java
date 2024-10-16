package pudding.toy.ourJourney.content.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.auth.service.AuthService;
import pudding.toy.ourJourney.content.dto.*;
import pudding.toy.ourJourney.content.service.ContentService;
import pudding.toy.ourJourney.global.response.BaseResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Content API", description = "Content API 입니다.")
@RequestMapping("/contents")
public class ContentController {
    private final ContentService contentService;
    private final AuthService authService;

    @GetMapping()
    @Operation(summary = "content 보기", description = "content를 검색한다.")
    public BaseResponse<?> getAllContents(
            Pageable pageable,
            @RequestParam("categoryId") Optional<Long> categoryId,
            @RequestParam("title") Optional<String> title,
            @RequestParam("tagIds") Optional<List<Long>> tagIds
    ) {
        return BaseResponse.ok(new GetContentResponse(contentService.getAllContents(pageable, categoryId, title, tagIds)));
    }

    @PostMapping
    @Operation(summary = "content 작성", description = "content를 작성한다.")
    public CreateContentResponse createNewContent(@RequestBody @Valid CreateContentRequest createContentRequest) {
        return new CreateContentResponse(contentService.createContent(createContentRequest, authService.getProfileWithAuthorize()));
    }

    @GetMapping("/{contentId}")
    @Operation(summary = "content 하나 조회", description = "content 한 개 조회한다.")
    public DetailContentResponse getDetailContent(@PathVariable("contentId") Long contentId, @RequestHeader(value = "Authorization", required = false) String header) {
        return contentService.getDetailContent(contentId, authService.getProfileWithAnonymous(header));
    }

    @PatchMapping("/{contentId}")
    @Operation(summary = "content 수정", description = "content 한 개 수정한다.")
    public void updateContent(@PathVariable("contentId") Long contentId, @RequestBody UpdateContentRequest editContent) {
        contentService.updateContent(contentId, editContent, authService.getProfileWithAuthorize());
    }

    @DeleteMapping("/{contentId}")
    @Operation(summary = "content 삭제", description = "content 삭제한다.")
    public void deleteContent(@PathVariable("contentId") Long contentId) {
        contentService.deleteContent(contentId, authService.getProfileWithAuthorize());
    }

    @Operation(summary = "글에 좋아요 누르기")
    @PostMapping("/{contentId}/likes")
    public NewLikeResponse addLikesToContent(@PathVariable("contentId") Long contentId) {
        return new NewLikeResponse(contentService.addLikesToContent(contentId, authService.getProfileWithAuthorize()));
    }

    @Operation(summary = "글에 좋아요 취소하기")
    @DeleteMapping("/{contentId}/likes")
    public void deleteLikesToContent(@PathVariable("contentId") Long contentId) {
        contentService.deleteLike(contentId, authService.getProfileWithAuthorize());
    }
}
