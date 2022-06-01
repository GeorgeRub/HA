package com.ha.back.repositories.account;

import com.ha.back.models.account.Account;
import com.ha.back.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("select a from Account a where a.ac_id = :ac_id and a.user = :user")
    Account findByAcIdAndUserId(String ac_id, User user);
    boolean existsByAccountNameAndUser_Id(String accountName, Long id);
    List<Account> findByUser_Id(Long id);

}
