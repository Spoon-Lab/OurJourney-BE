package pudding.toy.ourJourney.global.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pudding.toy.ourJourney.global.error.ErrorCode;
import pudding.toy.ourJourney.global.exception.CustomException;
import pudding.toy.ourJourney.global.response.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // ResponseStatusException 처리
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleResponseStatusException(CustomException e) {
        return new ResponseEntity<>(BaseResponse.fail(e.getErrorCode()), e.getErrorCode().getStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleResponseStatusException(Exception e) {
        return new ResponseEntity<>(BaseResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ResponseEntity<BaseResponse> handleIllegalArgumentException(IllegalArgumentException e) {
//        return new ResponseEntity<>(ErrorResponse.error(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    protected ResponseEntity<BaseResponse> handleNotFoundException(ChangeSetPersister.NotFoundException e) {
//        return new ResponseEntity<>(ErrorResponse.error(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(DuplicateKeyException.class)
//    protected ResponseEntity<BaseResponse> handleDuplicateKeyException(DuplicateKeyException e) {
//        return new ResponseEntity<>(ErrorResponse.error(HttpStatus.CONFLICT, e.getMessage()), HttpStatus.CONFLICT);
//    }

}
