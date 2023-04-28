package com.skypro.demo.exception.handlers;

import com.skypro.demo.exception.PostNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundControllerAdvice {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> notFound() {
        return ResponseEntity.notFound().build();
    }
}
