package com.tyssSpark.recrutounitservice.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@NoArgsConstructor
public class MaliciousCodeFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public MaliciousCodeFoundException(String message){
        super(message);
    }
    public MaliciousCodeFoundException(Exception exception, HttpStatus internalServerError) {
    }

    @ExceptionHandler(value = MaliciousCodeFoundException.class)
    public MaliciousCodeFoundException InvalidataExceptionexception(Exception exception) {
        exception.printStackTrace();
        return new MaliciousCodeFoundException(exception,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
