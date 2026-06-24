package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleException(Exception exception) {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(false);
        response.setUserId("john_doe_17091999");
        response.setEmail("john@xyz.com");
        response.setRollNumber("ABCD123");
        response.setSum("0");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
