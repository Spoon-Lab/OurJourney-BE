package pudding.toy.ourJourney.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.config.ProfileInitializer;
import pudding.toy.ourJourney.dto.category.GetCategoriesResponse;
import pudding.toy.ourJourney.service.ProfileService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Category API", description = "Category API 입니다.")
@RequestMapping("/follow")
public class FollowController {
    private final ProfileInitializer profileInitializer;
    private final ProfileService profileService;
    @PostMapping("/{id}")
    @Operation(summary = "팔로우 하기", description = "다른 사용자를 팔로우 한다.")
    public void follow(@PathVariable("id") Long id){
        profileService.followProfile(profileInitializer.dummyProfile,id);

    }



}
