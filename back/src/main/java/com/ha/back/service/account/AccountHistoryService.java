package com.ha.back.service.account;

import com.ha.back.repositories.account.AccountHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHistoryService {

    @Autowired
    private AccountHistoryRepository historyRepository;

}
