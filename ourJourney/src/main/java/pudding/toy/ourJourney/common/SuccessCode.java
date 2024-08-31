package pudding.toy.ourJourney.common;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SuccessCode implements BaseStatusCode{
    //200 ok.
    Success(200,"Success.");
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
