package com.oksocios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class ObjectNotAccesibleException extends Exception{
    public ObjectNotAccesibleException(String message){
        super(message);
    }
}
