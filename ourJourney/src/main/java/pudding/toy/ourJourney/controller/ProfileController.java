package pudding.toy.ourJourney.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.dto.profile.CreateProfileRequest;

@Tag(name = "Profile API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {
    @Operation(summary = "프로필 생성", description = "장고 서버에서 회원가입이 완료되면 호출합니다.")
    @PostMapping("")
    public void createProfile(@RequestBody CreateProfileRequest body) {
    }
}
