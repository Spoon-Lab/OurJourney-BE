package pudding.toy.ourJourney.config.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    int status;
    String message;

    public static ErrorResponse error(HttpStatus status, String message) {
        return new ErrorResponse(status.value(), message);
    }

    public static ErrorResponse error(int status, String message) {
        return new ErrorResponse(status, message);
    }
}
