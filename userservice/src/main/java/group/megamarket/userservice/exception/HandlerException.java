package group.megamarket.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundException(UserNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionHandler(Exception e){
        return "Something goes wrong";
    }

}
