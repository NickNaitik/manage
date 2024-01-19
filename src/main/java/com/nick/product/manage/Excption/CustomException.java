package com.nick.product.manage.Excption;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomException extends RuntimeException{

    private String message;
    public CustomException(String message){
        this.message=message;

    }
}
