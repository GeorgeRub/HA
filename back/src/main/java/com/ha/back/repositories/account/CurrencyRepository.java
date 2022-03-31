package com.ha.back.repositories.account;

import com.ha.back.models.account.currency.Currency;
import com.ha.back.models.account.currency.ECurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findByName(ECurrency name);
}
