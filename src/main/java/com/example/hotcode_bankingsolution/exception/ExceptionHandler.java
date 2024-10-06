package com.example.hotcode_bankingsolution.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Error> notFound(NotFoundException e) {
        return new ResponseEntity<>(new Error(e.getMessage(), NOT_FOUND.value()), NOT_FOUND);
    }

}
