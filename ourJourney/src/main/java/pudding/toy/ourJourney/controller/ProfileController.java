package pudding.toy.ourJourney.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.dto.profile.CreateProfileRequest;
import pudding.toy.ourJourney.dto.profile.GetDetailProfileResponse;

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
}
