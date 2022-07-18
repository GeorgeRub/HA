package com.ha.back.exceptions.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SaveSpendMoneyException extends RuntimeException {
    public SaveSpendMoneyException() {
    }

    public SaveSpendMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveSpendMoneyException(String message){
        super(message);
    }
}
