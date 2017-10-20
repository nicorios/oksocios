package com.oksocios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ObjectAlreadyExistsException extends Exception{
    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
