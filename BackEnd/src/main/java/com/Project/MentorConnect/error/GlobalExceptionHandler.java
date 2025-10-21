package com.Project.MentorConnect.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Time;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUsernamePasswordNotFoundException(UsernameNotFoundException e){
        ApiError apiError = new ApiError("Username not found with password ", e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}
