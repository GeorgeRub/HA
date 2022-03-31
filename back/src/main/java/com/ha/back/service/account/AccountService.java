package com.ha.back.service.account;

import com.ha.back.models.account.Account;
import com.ha.back.models.user.User;
import com.ha.back.repositories.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAllByUser(User user) {
        return accountRepository.findByUser_Id(user.getId());
    }

    public boolean exist(User user, String name) {
        return accountRepository.existsByAccountNameAndUser_Id(name, user.getId());
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
