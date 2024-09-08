package pudding.toy.ourJourney.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.dto.profile.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Profile API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {
    @Operation(summary = "프로필 생성", description = "장고 서버에서 회원가입이 완료되면 호출합니다.")
    @PostMapping("")
    public void createProfile(@RequestBody CreateProfileRequest body) {
    }

    @Operation(summary = "프로필 조회")
    @GetMapping("/{id}")
    public GetDetailProfileResponse getProfile(@PathVariable Long id) {
        return new GetDetailProfileResponse(1L, Optional.of("nickname"), null, null);
    }

    // TODO: login_required && is_owner
    @Operation(summary = "프로필 수정")
    @PatchMapping("/{id}")
    public void updateProfile(
            @PathVariable Long id,
            @RequestParam(required = false) Optional<String> nickname,
            @RequestParam(required = false) Optional<String> imageUrl,
            @RequestParam(required = false) Optional<String> selfIntroduction
    ) {

    }

    @Operation(summary = "내가 작성한 글 가져오기")
    @GetMapping("/{id}/contents")
    public GetMyContentsResponse getMyContents(@PathVariable Long id, @PageableDefault Pageable pageable) {
        List<GetMyContentsDto> list = List.of(new GetMyContentsDto(1L, "title", 1L, "imageUrl", null, null));

        return new GetMyContentsResponse(new PageImpl<>(list, pageable, 1));
    }

    @Operation(summary = "내가 작성한 댓글 가져오기")
    @GetMapping("/{id}/comments")
    public GetMyCommentsResponse getMyComments(@PathVariable Long id, @PageableDefault Pageable pageable) {
        List<GetMyCommentsDto> list = List.of(new GetMyCommentsDto(1L, "contents", 1L, null, null));

        return new GetMyCommentsResponse(new PageImpl<>(list, pageable, 1));
    }

    @Operation(summary = "내가 좋아요한 글 가져오기")
    @GetMapping("/{id}/likes/contents")
    public GetLikeContentsResponse getLikesContents(@PathVariable Long id, @PageableDefault Pageable pageable) {
        List<GetLikesContentsDto> list = List.of(new GetLikesContentsDto(1L, "title", 1L, "imageUrl", null, null));

        return new GetLikeContentsResponse(new PageImpl<>(list, pageable, 1));
    }
}
