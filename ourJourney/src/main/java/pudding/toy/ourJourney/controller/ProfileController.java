package pudding.toy.ourJourney.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.dto.auth.ProfileAuthRequest;
import pudding.toy.ourJourney.dto.profile.*;
import pudding.toy.ourJourney.service.ProfileService;

import java.util.List;

@Tag(name = "Profile API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;
    @Operation(summary = "프로필 생성", description = "장고 서버에서 회원가입이 완료되면 호출합니다.")
    @PostMapping("")
    public NewProfileResponse createProfile(@RequestBody ProfileAuthRequest body) {
        return profileService.createProfile(body);
    }

    @Operation(summary = "프로필 조회")
    @GetMapping("/{id}")
    public GetDetailProfileResponse getProfile(@PathVariable Long id) {
        return profileService.getDetailProfile(id);
    }

    // TODO: login_required && is_owner
    @Operation(summary = "프로필 수정")
    @PatchMapping("/{id}")
    public void updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest body) {
        profileService.updateMyProfile(id,body);
    }

    @Operation(summary = "해당 유저가 작성한 글 가져오기")
    @GetMapping("/{id}/contents")
    public GetMyContentsResponse getMyContents(@PathVariable Long id, @PageableDefault Pageable pageable) {
       return profileService.getMyContents(id,pageable); //todo: id 빼기.
    }

    @Operation(summary = "내가 작성한 댓글 가져오기")
    @GetMapping("/{id}/comments")
    public GetMyCommentsResponse getMyComments(@PathVariable Long id, @PageableDefault Pageable pageable) {
        return profileService.getMyComments(id,pageable);
    }
    @Operation(summary = "내가 좋아요한 글 가져오기")
    @GetMapping("/{id}/likes/contents")
    public GetLikeContentsResponse getLikesContents(@PathVariable Long id, @PageableDefault Pageable pageable) {
        return profileService.getMyLikeContents(id,pageable);
    }
}
