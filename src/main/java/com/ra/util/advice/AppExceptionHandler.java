package com.ra.util.advice;

import com.ra.util.exception.CategoryException;
import com.ra.util.exception.UserException;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> invalidRequest(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>() ;
        e.getBindingResult().getFieldErrors().forEach(item -> {
            errors.put(item.getField(), item.getDefaultMessage()) ;
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST) ;
    }

    // TODO : USER
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserException.class)
    public String userException(UserException userException) {
        return userException.getMessage();
    }
    // TODO : CATEGORY
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CategoryException.class)
    public String categoryException(CategoryException categoryException) {
        return categoryException.getMessage();
    }

}
