package pudding.toy.ourJourney.common;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
public enum ErrorCode implements BaseStatusCode{ //todo: 더 명확하게 에러코드 명시.
    // 400 클라이언트의 요청 파라미터 오류
    BAD_REQUEST(400,"Bad request."),

    // 401 권한이 없는 오류
    UNAUTHORIZED(401,"Unauthorized."),

    // 403
    FORBIDDEN(403, "Forbidden."),

    // 404
    NOT_FOUND(404,"Not Found."),

    // 405
    METHOD_NOT_ALLOWED(405,"Method is not allowed.");
    private final int code;
    private final String message;
    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
