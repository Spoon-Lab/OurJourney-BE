package pudding.toy.ourJourney.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pudding.toy.ourJourney.common.response.BaseResponse;
import pudding.toy.ourJourney.dto.ContentEditRequestDto;
import pudding.toy.ourJourney.dto.ContentPostRequestDto;
import pudding.toy.ourJourney.dto.ContentRequestDto;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.ContentService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Content API", description = "Thread API 입니다.")
@RequestMapping("/api/thread")
public class ThreadController {
    private final AuthService authService;
    private final ContentService contentService;
    @GetMapping
    @Operation(summary = "thread 보기", description = "thread를 검색한다.")
    public BaseResponse<?> getAllContent(HttpServletRequest httpServletRequest,@RequestBody ContentRequestDto contentRequestDto){
        if(authService.validateAuth(httpServletRequest.getHeader("Authorization"))){
            return contentService.getAllContents(contentRequestDto);
        }
        return null;
    }
    @PostMapping
    @Operation(summary = "content 작성", description = "content를 작성한다.")
    public BaseResponse<?> postContent(HttpServletRequest httpServletRequest,@RequestBody ContentPostRequestDto contentPostRequestDto){
        if(authService.validateAuth(httpServletRequest.getHeader("Authorization"))){
            return BaseResponse.ok(contentService.createContent(contentPostRequestDto));
        }
        return null;
    }
    @GetMapping("/{contentId}")
    @Operation(summary = "content 하나 조회", description = "content 한 개 조회한다.")
    public BaseResponse<?> getOneContent(HttpServletRequest httpServletRequest,@PathVariable("contentId") Long contentId){
        if(authService.validateAuth(httpServletRequest.getHeader("Authorization"))){
            return BaseResponse.ok(contentService.getOneContent(contentId));
        }
        return null;
    }
    @PatchMapping ("/{contentId}")
    @Operation(summary = "content 수정", description = "content 한 개 수정한다.")
    public BaseResponse<?> editOneContent(HttpServletRequest httpServletRequest,@PathVariable("contentId") Long contentId,@RequestBody ContentEditRequestDto contentEditRequestDto){
        if(authService.validateAuth(httpServletRequest.getHeader("Authorization"))){
            return BaseResponse.ok(contentService.editOneContent(contentId,contentEditRequestDto));
        }
        return null;
    }
    @DeleteMapping ("/{contentId}")
    @Operation(summary = "content 삭제", description = "content 삭제한다.")
    public BaseResponse<?> editOneContent(HttpServletRequest httpServletRequest,@PathVariable("contentId") Long contentId){
        if(authService.validateAuth(httpServletRequest.getHeader("Authorization"))){
            contentService.deleteOneContent(contentId);
            return BaseResponse.ok();
        }
        return null;
    }



}
