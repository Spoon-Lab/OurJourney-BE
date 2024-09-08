package pudding.toy.ourJourney.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pudding.toy.ourJourney.dto.tags.CreateTagsRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagsController {

    @Operation(summary = "해시태그 생성")
    @PostMapping("")
    public void createTag(@RequestBody @Valid CreateTagsRequest createTagsRequest) {
    }
}
