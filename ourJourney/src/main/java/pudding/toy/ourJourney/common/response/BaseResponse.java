package pudding.toy.ourJourney.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import pudding.toy.ourJourney.common.HttpStatusCode;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter //json 직렬화를 위해 필요
@Builder(access = AccessLevel.PRIVATE)
public class BaseResponse<T>{
    HttpStatus code;
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;

    /**
     * 요청에 성공했고, 응답 정보가 없는 경우.
     * @return
     * @param <T>
     */
    public static <T>BaseResponse<T>ok(){
        return BaseResponse.<T>builder()
                .code(HttpStatusCode.SUCCESS.getCode())
                .message(HttpStatusCode.SUCCESS.getMessage())
                .build();
    }

    /**
     * 요청에 성공했고, 응답 정보가 있는 경우.
     * @param data
     * @return
     * @param <T>
     */
    public static <T>BaseResponse<T> ok(T data){
        return BaseResponse.<T>builder()
                .code(HttpStatusCode.SUCCESS.getCode())
                .message(HttpStatusCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }
    /**
     * 요청에 실패한 경우
     * @return
     * @param <T>
     */
    public static <T>BaseResponse<T> fail(HttpStatusCode httpStatusCode){
        return BaseResponse.<T>builder()
                .code(httpStatusCode.getCode())
                .message(httpStatusCode.getMessage())
                .build();
    }
}
