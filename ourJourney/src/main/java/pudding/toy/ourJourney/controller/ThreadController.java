package pudding.toy.ourJourney.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.dto.thread.*;
import pudding.toy.ourJourney.entity.ContentsThread;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.ThreadService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Thread API", description = "Thread API 입니다.")
@RequestMapping("/contents")
public class ThreadController {
    private final ThreadService threadService;
    private final AuthService authService;

    @GetMapping("/{contentId}/threads")
    @Operation(summary = "thread 보기", description = "thread를 목록을 본다.")
    public GetThreadResponse getAllThreads(@PathVariable("contentId") Long contentId, @PageableDefault() Pageable pageable, @RequestHeader(value = "Authorization", required = false) String header) {
        return new GetThreadResponse(threadService.getThreads(contentId, pageable, authService.getProfileWithAnonymous(header)));
    }

    @GetMapping("/{contentId}/threads/{threadId}")
    @Operation(summary = "thread 보기", description = "thread를 목록을 본다.")
    public ThreadDetailResponse getAllThreads(@PathVariable("contentId") Long contentId, @PathVariable("threadId") Long threadId, @RequestHeader(value = "Authorization", required = false) String header) {
        return threadService.getThreadsDetail(contentId, threadId, authService.getProfileWithAnonymous(header));
    }

    @PostMapping("/{contentId}/threads")
    @Operation(summary = "thread 작성", description = "thread를 작성한다.")
    public CreateThreadResponse createNewThread(@PathVariable("contentId") Long contentId, @RequestBody @Valid CreateThreadRequest body) {
        ContentsThread threads = threadService.createThreads(authService.getProfileWithAuthorize(), contentId, body);
        return new CreateThreadResponse(threads.getId());
    }

    @PatchMapping("/{contentId}/threads/{threadId}")
    @Operation(summary = "thread 수정", description = "thread를 수정한다.")
    public void updateThread(@PathVariable("contentId") Long contentId, @PathVariable("threadId") Long threadId, @RequestBody @Valid UpdateThreadRequest body) {
        threadService.updateThread(contentId, threadId, body, authService.getProfileWithAuthorize());
    }

    @DeleteMapping("/{contentId}/threads/{threadId}")
    @Operation(summary = "thread 삭제", description = "thread 삭제한다.")
    public void deleteThread(@PathVariable("contentId") Long contentId, @PathVariable("threadId") Long threadId) {
        authService.getProfileWithAuthorize();
        threadService.deleteThread(contentId, threadId, authService.getProfileWithAuthorize());
    }
}
