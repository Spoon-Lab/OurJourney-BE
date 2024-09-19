package pudding.toy.ourJourney.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.config.DummyDataInitializer;
import pudding.toy.ourJourney.dto.content.*;
import pudding.toy.ourJourney.entity.Profile;
import pudding.toy.ourJourney.service.ContentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Content API", description = "Content API 입니다.")
@RequestMapping("/contents")
public class ContentController {
    private final ContentService contentService;
    private final DummyDataInitializer dummyDataInitializer;

    @GetMapping()
    @Operation(summary = "content 보기", description = "content를 검색한다.")
    public GetContentResponse getAllContents(Pageable pageable, @RequestParam Optional<Long> categoryId, @RequestParam Optional<String> title, @RequestParam Optional<List<Long>> tagIds) {
        return new GetContentResponse(contentService.getAllContents(pageable, categoryId, title, tagIds));
    }

    @PostMapping
    @Operation(summary = "content 작성", description = "content를 작성한다.")
    public CreateContentResponse createNewContent(@RequestBody @Valid CreateContentRequest createContentRequest) {
        //유저 받아오는 로직
        return new CreateContentResponse(contentService.createContent(createContentRequest, dummyDataInitializer.dummyProfile));
    }

    @GetMapping("/{contentId}")
    @Operation(summary = "content 하나 조회", description = "content 한 개 조회한다.")
    public DetailContentResponse getDetailContent(@PathVariable("contentId") Long contentId) {
        return contentService.getDetailContent(contentId);
    }

    @PatchMapping("/{contentId}")
    @Operation(summary = "content 수정", description = "content 한 개 수정한다.")
    public void updateContent(@PathVariable("contentId") Long contentId, @RequestBody UpdateContentRequest editContent) {
        contentService.updateContent(contentId, editContent);
    }

    @DeleteMapping("/{contentId}")
    @Operation(summary = "content 삭제", description = "content 삭제한다.")
    public void deleteContent(@PathVariable("contentId") Long contentId) {
        contentService.deleteContent(contentId);
    }

    @Operation(summary = "글에 좋아요 누르기")
    @PostMapping("/{contentId}/likes")
    public Long addLikesToContent(@PathVariable("contentId") Long contentId) {
        Profile profile = dummyDataInitializer.dummyProfile; //dummy
        return contentService.addLikesToContent(contentId, profile);
    }

    @Operation(summary = "글에 좋아요 취소하기")
    @DeleteMapping("/{contentId}/likes")
    public void deleteLikesToContent(@PathVariable("contentId") Long contentId) {
        Profile profile = dummyDataInitializer.dummyProfile; //dummy
        contentService.deleteLike(contentId, profile);
    }


}
