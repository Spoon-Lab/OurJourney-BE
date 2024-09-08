package pudding.toy.ourJourney.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.dto.tags.CreateTagsRequest;
import pudding.toy.ourJourney.dto.tags.GetTagsDto;
import pudding.toy.ourJourney.dto.tags.GetTagsResponse;

import java.util.List;

@Tag(name = "Tags API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagsController {

    @Operation(summary = "해시태그 생성")
    @PostMapping("")
    public void createTag(@RequestBody @Valid CreateTagsRequest createTagsRequest) {
    }

    @Operation(summary = "해시태그 가져오기", description = "해시태그 자동완성할때 호출")
    @GetMapping("")
    public GetTagsResponse getTags(@PageableDefault Pageable pageable, @RequestParam() String tagName) {
        List<GetTagsDto> list = List.of(new GetTagsDto(1L, "tag1"));
        return new GetTagsResponse(new PageImpl<>(list, pageable, 1L));
    }
}
