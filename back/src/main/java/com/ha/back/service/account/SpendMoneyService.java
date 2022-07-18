package com.ha.back.service.account;

import com.ha.back.exceptions.account.CurrencyFoundException;
import com.ha.back.exceptions.account.SaveSpendMoneyException;
import com.ha.back.models.account.SpendMoneyModel;
import com.ha.back.models.account.currency.Currency;
import com.ha.back.payload.request.account.SpendMoney;
import com.ha.back.repositories.account.SpendMoneyRepository;
import com.ha.back.service.account.currency.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpendMoneyService {

    private SpendMoneyRepository spendMoneyRepository;
    private CurrencyService currencyService;

    public SpendMoneyModel save(SpendMoney spendMoney) {
        SpendMoneyModel spendMoneyModel = new SpendMoneyModel();
        spendMoneyModel.setDate(spendMoney.getDate());
        spendMoneyModel.setSpendName(spendMoney.getSpendName());
        spendMoneyModel.setTotalPrice(spendMoney.getTotalPrice());
        if (spendMoney.isDifferentCurrency()) {
            Currency currency = currencyService.findByCurrencyName(spendMoney.getCurrency());
            if (currency == null)
                throw new CurrencyFoundException("Error to find currency " + spendMoney.getCurrency() + "!");
            System.out.println("currency");
            System.out.println(currency);
            spendMoneyModel.setCurrency(currency);
            spendMoneyModel.setExchangePrice(spendMoney.getExchangePrice());
            spendMoneyModel.setTotalPriceInCurrency(spendMoney.getTotalPriceInCurrency());
            spendMoneyModel.setDifferentCurrency(spendMoney.isDifferentCurrency());
        }
        spendMoneyModel = save(spendMoneyModel);
        if (spendMoneyModel.getId() == null) throw new SaveSpendMoneyException("SpendMoney not saved!");
        return spendMoneyModel;
    }

    public SpendMoneyModel save(SpendMoneyModel spendMoney) {
        return spendMoneyRepository.save(spendMoney);
    }

}
