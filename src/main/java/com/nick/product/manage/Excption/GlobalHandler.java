package com.nick.product.manage.Excption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomError(CustomException ce){

        if(ce.getMessage() == null)
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Name or Password is Wrong!");
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ce.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Name or Password is Wrong!");
    }

}
