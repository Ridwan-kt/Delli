package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    // This class can be used to handle exceptions globally
    // You can define methods here to handle specific exceptions
    // For example, you can handle ProductNotFoundException here

    // Example method to handle ProductNotFoundException

     @ExceptionHandler(ProductNotFoundException.class)
     public ResponseEntity<String> handle(ProductNotFoundException pnfex) {
         return new ResponseEntity<>(
                 pnfex.getMessage(),
                 HttpStatus.NOT_FOUND
         );
     }
    // Example method to handle ProductAlreadyExistException
     @ExceptionHandler(ProductAlreadyExistException.class)
     public ResponseEntity<String> handle(ProductAlreadyExistException paeex) {
        return new ResponseEntity<>(
            paeex.getMessage(),
            HttpStatus.CONFLICT
        );
    }
}
