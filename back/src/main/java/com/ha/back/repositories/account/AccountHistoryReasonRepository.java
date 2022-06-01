package com.ha.back.repositories.account;

import com.ha.back.models.account.AccountHistoryReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHistoryReasonRepository extends JpaRepository<AccountHistoryReason, String> {
    AccountHistoryReason findByNameIgnoreCase(String name);

}
