package pudding.toy.ourJourney.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.dto.content.*;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.ContentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "Content API", description = "Content API 입니다.")
@RequestMapping("/contents")
public class ContentController {
    private final AuthService authService;
    private final ContentService contentService;

    @GetMapping()
    @Operation(summary = "content 보기", description = "content를 검색한다.")
    public GetContentResponse getAllContents(Pageable pageable, @RequestParam Optional<Long> categoryId, @RequestParam Optional<String> content, @RequestParam Optional<List<Long>> tagIds) {
        //todo: 더 공부하고 고치기,,일단 틀만 잡음!
        List<ListContentDto> list = List.of(new ListContentDto());
        return new GetContentResponse(new PageImpl<>(list, pageable, 1L));
    }

    @PostMapping
    @Operation(summary = "content 작성", description = "content를 작성한다.")
    public CreateContentResponse createNewContent(@RequestBody @Valid CreateContentRequest createContentRequest) {
        return new CreateContentResponse(contentService.createContent(createContentRequest));
    }

    @GetMapping("/{contentId}")
    @Operation(summary = "content 하나 조회", description = "content 한 개 조회한다.")
    public DetailContentResponse getDetailContent(@PathVariable("contentId") Long contentId) {
        return new DetailContentResponse();
    }

    @PatchMapping("/{contentId}")
    @Operation(summary = "content 수정", description = "content 한 개 수정한다.")
    public void updateContent(@PathVariable("contentId") Long contentId, @RequestBody EditContentRequest editContent) {

    }

    @DeleteMapping("/{contentId}")
    @Operation(summary = "content 삭제", description = "content 삭제한다.")
    public void deleteContent(@PathVariable("contentId") Long contentId) {

    }


}
