package com.ha.back.service.account;

import com.ha.back.models.account.AccountHistoryReason;
import com.ha.back.repositories.account.AccountHistoryReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHistoryReasonService {

    @Autowired
    AccountHistoryReasonRepository historyReasonRepository;

    public AccountHistoryReason findByNameIgnoreCase(String name) {
        return historyReasonRepository.findByNameIgnoreCase(name);
    }

    public AccountHistoryReason save(AccountHistoryReason reason) {
        return historyReasonRepository.save(reason);
    }
}
