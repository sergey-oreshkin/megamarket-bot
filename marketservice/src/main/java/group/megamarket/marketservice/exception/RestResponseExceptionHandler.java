package group.megamarket.marketservice.exception;

import group.megamarket.marketservice.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ForbiddenException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                                                 .errorMessage(exception.getMessage())
                                                 .errorCode(HttpStatus.FORBIDDEN.toString())
                                                 .build(), HttpStatus.FORBIDDEN);
    }
}
