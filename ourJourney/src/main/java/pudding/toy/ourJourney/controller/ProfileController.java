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
import pudding.toy.ourJourney.utils.AuthUtils;

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
    @Operation(summary = "내 프로필 조회")
    @GetMapping("")
    public GetDetailProfileResponse getProfile() {
        return profileService.getDetailProfile(AuthUtils.currentUser());
    }

    @Operation(summary = "프로필 조회")
    @GetMapping("/{id}")
    public GetDetailProfileResponse getProfile(@PathVariable("id") Long id) {
        return profileService.getDetailProfile(id);
    }

    @Operation(summary = "내 프로필 수정")
    @PatchMapping("")
    public void updateProfile(@RequestBody UpdateProfileRequest body) {
        profileService.updateMyProfile(AuthUtils.currentUser(),body);
    }

    @Operation(summary = "해당 유저가 작성한 글 가져오기")
    @GetMapping("/contents")
    public GetMyContentsResponse getMyContents(@PageableDefault Pageable pageable) {
        return profileService.getMyContents(AuthUtils.currentUser(), pageable);
    }

    @Operation(summary = "내가 작성한 댓글 가져오기")
    @GetMapping("/comments")
    public GetMyCommentsResponse getMyComments(@PageableDefault Pageable pageable) {
        return profileService.getMyComments(AuthUtils.currentUser(), pageable);
    }

    @Operation(summary = "내가 좋아요한 글 가져오기")
    @GetMapping("/likes/contents")
    public GetLikeContentsResponse getLikesContents(@PageableDefault Pageable pageable) {
        return profileService.getMyLikeContents(AuthUtils.currentUser(), pageable);
    }
}

