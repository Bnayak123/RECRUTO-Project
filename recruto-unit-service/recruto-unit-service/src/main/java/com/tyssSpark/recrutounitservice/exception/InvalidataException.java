package com.tyssSpark.recrutounitservice.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
@NoArgsConstructor
public class InvalidataException extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidataException(String message){
        super(message);
    }
    public InvalidataException(Exception exception, HttpStatus internalServerError) {
    }

    @ExceptionHandler(value = InvalidataException.class)
    public InvalidataException InvalidataExceptionexception(Exception exception) {
       exception.printStackTrace();
        return new InvalidataException(exception,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
