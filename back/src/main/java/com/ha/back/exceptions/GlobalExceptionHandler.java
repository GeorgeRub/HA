package com.ha.back.exceptions;

import com.ha.back.exceptions.account.NoFoundAnyAccount;
import com.ha.back.exceptions.currency.NotFoundCurrency;
import com.ha.back.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFountAnyUser.class, NoFoundAnyAccount.class, NotFoundCurrency.class})
    public ResponseEntity<?> notFountAnyUser(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(ex.getLocalizedMessage()));
    }

}
