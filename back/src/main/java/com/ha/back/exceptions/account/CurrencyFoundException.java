package com.ha.back.exceptions.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CurrencyFoundException extends RuntimeException {
    public CurrencyFoundException() {
    }

    public CurrencyFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyFoundException(String message){
        super(message);
    }
}
