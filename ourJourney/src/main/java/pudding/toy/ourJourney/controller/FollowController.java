package pudding.toy.ourJourney.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.config.DummyDataInitializer;
import pudding.toy.ourJourney.service.FollowService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Follow API", description = "Follow API 입니다.")
@RequestMapping("/profile")
public class FollowController {
    private final DummyDataInitializer dummyDataInitializer;
    private final FollowService followService;

    @PostMapping("/{id}/follow")
    @Operation(summary = "팔로우 하기", description = "다른 사용자를 팔로우 한다.")
    public void follow(@PathVariable("id") Long id) {
        followService.followProfile(dummyDataInitializer.dummyProfile, id);
    }

    @PostMapping("/{id}/unfollow")
    @Operation(summary = "팔로우 취소하기", description = "다른 사용자를 언팔로우한다.")
    public void unFollow(@PathVariable("id") Long id) {
        followService.unFollow(dummyDataInitializer.dummyProfile, id);
    }

}
