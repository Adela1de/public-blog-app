package com.example.publicblogapp.controllers.exceptions;

import com.example.publicblogapp.exceptions.ObjectNotFoundException;
import com.example.publicblogapp.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request)
    {
        var error = new StandardError(System.currentTimeMillis(), e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> httpRequestMethodNotSupportedException
            (HttpRequestMethodNotSupportedException e, ServletRequest request)
    {
        var error = new StandardError(System.currentTimeMillis(), e.getMessage(), HttpStatus.NOT_IMPLEMENTED.value());
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<StandardError> userAlreadyExistsException
            (UserAlreadyExistsException e, ServletRequest request)
    {
        var error = new StandardError(System.currentTimeMillis(), e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
