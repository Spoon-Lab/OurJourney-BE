package pudding.toy.ourJourney.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.config.DummyDataInitializer;
import pudding.toy.ourJourney.dto.thread.*;
import pudding.toy.ourJourney.entity.ContentsThread;
import pudding.toy.ourJourney.service.ThreadService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Thread API", description = "Thread API 입니다.")
@RequestMapping("/contents")
public class ThreadController {
    private final DummyDataInitializer dummyDataInitializer;
    private final ThreadService threadService;

    @GetMapping("/{contentId}/threads")
    @Operation(summary = "thread 보기", description = "thread를 목록을 본다.")
    public GetThreadResponse getAllThreads(@PathVariable("contentId") Long contentId, @PageableDefault() Pageable pageable) {
        return new GetThreadResponse(threadService.getThreads(contentId, pageable));
    }

    @PostMapping("/{contentId}/threads")
    @Operation(summary = "thread 작성", description = "thread를 작성한다.")
    public CreateThreadResponse createNewThread(@PathVariable("contentId") Long contentId, @RequestBody @Valid CreateThreadRequest body) {
        ContentsThread threads = threadService.createThreads(dummyDataInitializer.dummyProfile, contentId, body.getTexts(), body.getTags(), body.getThreadImg());
        return new CreateThreadResponse(threads.getId());
    }

    @PatchMapping("/{contentId}/threads/{threadId}")
    @Operation(summary = "thread 수정", description = "thread를 수정한다.")
    public void updateThread(@PathVariable("contentId") Long contentId, @PathVariable("threadId") Long threadId, @RequestBody @Valid UpdateThreadRequest body) {
        threadService.updateThread(contentId, threadId, body);
    }

    @DeleteMapping("/{contentId}/threads/{threadId}")
    @Operation(summary = "content 삭제", description = "content 삭제한다.")
    public void deleteThread(@PathVariable("contentId") Long contentId, @PathVariable("threadId") Long threadId) {
        threadService.deleteThread(contentId, threadId);
    }

}
