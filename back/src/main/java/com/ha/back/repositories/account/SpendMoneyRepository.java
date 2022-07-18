package com.ha.back.repositories.account;

import com.ha.back.models.account.SpendMoneyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendMoneyRepository extends JpaRepository<SpendMoneyModel, String> {
}
