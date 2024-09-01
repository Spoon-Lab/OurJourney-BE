package pudding.toy.ourJourney.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum HttpStatusCode { //todo: 더 명확하게 에러코드 명시.
    SUCCESS(HttpStatus.OK,"Success."),
    // 400 클라이언트의 요청 파라미터 오류
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"Bad request."),

    // 401 권한이 없는 오류
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"Unauthorized."),

    // 403
    FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden."),

    // 404
    NOT_FOUND(HttpStatus.NOT_FOUND,"Not Found."),

    // 405
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"Method is not allowed."),

    //409
    CONFLICT(HttpStatus.CONFLICT,"중복되었습니다.");
    private final HttpStatus code;
    private final String message;
    public HttpStatus getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
