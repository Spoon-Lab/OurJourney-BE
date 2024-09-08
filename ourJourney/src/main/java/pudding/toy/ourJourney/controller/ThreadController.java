package pudding.toy.ourJourney.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.dto.thread.*;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.ContentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Thread API", description = "Thread API 입니다.")
@RequestMapping("/api/content")
public class ThreadController {
    private final AuthService authService;
    private final ContentService contentService;

    @GetMapping("/{contentId}/threads")
    @Operation(summary = "thread 보기", description = "thread를 목록을 본다.")
    public GetThreadResponse getAllThreads(@PathVariable("contentId") Long contentId, @PageableDefault() Pageable pageable) {
        //todo: 공부하고 고치기. 에러 남
        ThreadProfileDto threadProfileDto = new ThreadProfileDto(1L, "url", "nickname");
        GetThreadDto getThreadDto = new GetThreadDto(1L, "thread", threadProfileDto, LocalDateTime.now());
        List<GetThreadDto> list = List.of(getThreadDto);
        return new GetThreadResponse(new PageImpl<>(list, pageable, 1L));
    }

    @PostMapping("/{contentId}/threads")
    @Operation(summary = "thread 작성", description = "thread를 작성한다.")
    public CreateThreadResponseDto createThread(@PathVariable("contentId") Long contentId, @RequestBody @Valid CreateThreadRequestDto body) {
        return new CreateThreadResponseDto();
    }

    @PatchMapping("/{contentId}/threads/{threadId}")
    @Operation(summary = "thread 수정", description = "thread를 수정한다.")
    public void updateThread(@PathVariable("contentId") Long contentId, @PathVariable("threadId") Long threadId, @RequestBody @Valid ThreadEditRequestDto body) {

    }

    @DeleteMapping("/{contentId}/threads/{threadId}/")
    @Operation(summary = "content 삭제", description = "content 삭제한다.")
    public void deleteThread(@PathVariable("contentId") Long contentId, @PathVariable("threadId") Long threadId) {

    }


}
