package com.ha.back.repositories.account;

import com.ha.back.models.account.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, String> {
    @Query("select a from AccountHistory a where  a.accountId.ac_id = :id and a.createDate >= :startDate and a.createDate <= :endDate")
    List<AccountHistory> findByIdAndBetweenDates(String id, Date startDate, Date endDate);
}
