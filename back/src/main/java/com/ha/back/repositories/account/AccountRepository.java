package com.ha.back.repositories.account;

import com.ha.back.models.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    boolean existsByAccountNameAndUser_Id(String accountName, Long id);
    List<Account> findByUser_Id(Long id);

}
