package com.rei.interview.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity processIllegalArgumentException(IllegalArgumentException i){
        return new ResponseEntity(i.getMessage(), HttpStatus.NOT_FOUND);
    }
}
