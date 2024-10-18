package pudding.toy.ourJourney.global.exception;

import lombok.Getter;
import pudding.toy.ourJourney.global.error.ErrorCode;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode; //에러 상세 내용

    public CustomException(ErrorCode code) {
        this.errorCode = code;
    }

}
