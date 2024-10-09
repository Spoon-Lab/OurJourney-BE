package pudding.toy.ourJourney.tags.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.tags.dto.CreateTagResponse;
import pudding.toy.ourJourney.tags.dto.CreateTagsRequest;
import pudding.toy.ourJourney.tags.dto.GetTagsDto;
import pudding.toy.ourJourney.tags.dto.GetTagsResponse;
import pudding.toy.ourJourney.tags.service.TagService;

@Tag(name = "Tags API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagsController {
    private final TagService tagService;

    @Operation(summary = "해시태그 생성")
    @PostMapping("")
    public CreateTagResponse createTag(@RequestBody @Valid CreateTagsRequest createTagsRequest) {
        return new CreateTagResponse(tagService.createNewTags(createTagsRequest.getTagName()));
    }

    @Operation(summary = "해시태그 가져오기", description = "해시태그 자동완성할때 호출")
    @GetMapping("")
    public GetTagsResponse getTags(@PageableDefault Pageable pageable, @RequestParam("name") String tagName) {
        PageImpl<GetTagsDto> tags = tagService.getTags(tagName, pageable);
        return new GetTagsResponse(tags);
    }
}
