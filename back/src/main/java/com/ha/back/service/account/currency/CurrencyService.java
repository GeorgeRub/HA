package com.ha.back.service.account.currency;

import com.ha.back.exceptions.account.NotFoundException;
import com.ha.back.models.account.currency.Currency;
import com.ha.back.models.account.currency.ECurrency;
import com.ha.back.repositories.account.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Currency findByName(String currency){
        try {
            return currencyRepository.findByName(ECurrency.valueOf(currency));
        } catch (Exception e) {
            throw new NotFoundException("Not found currency by name " + currency + "!");
        }
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }
}
