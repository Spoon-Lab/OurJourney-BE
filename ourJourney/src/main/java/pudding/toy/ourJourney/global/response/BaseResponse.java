package pudding.toy.ourJourney.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import pudding.toy.ourJourney.global.error.ErrorCode;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값인 필드를 JSON 응답에 포함하지 않음
public class BaseResponse<T> {
    HttpStatus httpStatus;
    String code;
    String message;
    T data;

    public static BaseResponse<?> ok() {
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code("200")
                .message("요청에 성공했습니다.")
                .build();
    }

    public static <T> BaseResponse<?> ok(T data) {
        return BaseResponse.<T>builder()
                .httpStatus(HttpStatus.OK)
                .code("200")
                .message("요청에 성공했습니다.")
                .data(data)
                .build();
    }

    public static BaseResponse<?> fail(ErrorCode errorCode) {
        return BaseResponse.builder()
                .httpStatus(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

}
