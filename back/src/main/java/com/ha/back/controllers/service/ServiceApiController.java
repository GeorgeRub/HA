package com.ha.back.controllers.service;

import com.ha.back.models.account.currency.Currency;
import com.ha.back.service.account.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/service/")
public class ServiceApiController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping("currency/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> allCurrency() {
        try {
            List<Currency> currencies = currencyService.findAll();
            if (currencies == null || currencies.isEmpty())
                return new ResponseEntity<Object>("Currency error!", new HttpHeaders(), HttpStatus.NOT_FOUND);
            return ResponseEntity.ok(currencies);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Currency error!", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

}
