package com.ha.back.service.account;

import com.ha.back.models.account.AccountHistory;
import com.ha.back.repositories.account.AccountHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountHistoryService {

    @Autowired
    private AccountHistoryRepository historyRepository;

    public AccountHistory save(AccountHistory accountHistory) {
        return historyRepository.save(accountHistory);
    }

    public List<AccountHistory> findByIdAndBetweenDates(String id, Date startDate, Date endDate) {
        return historyRepository.findByIdAndBetweenDates(id, startDate, endDate);
    }
}
