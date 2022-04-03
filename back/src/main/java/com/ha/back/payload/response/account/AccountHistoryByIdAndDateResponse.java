package com.ha.back.payload.response.account;

import com.ha.back.models.account.AccountHistory;
import com.ha.back.models.account.AccountHistoryReason;

import java.util.Date;

public class AccountHistoryByIdAndDateResponse {

    private Double balance;
    private Double value;
    private AccountHistoryReason reason;
    private String id;
    private Date createDate;

    public AccountHistoryByIdAndDateResponse(AccountHistory accountHistory){
        this.balance = accountHistory.getBalance();
        this.value = accountHistory.getValue();
        this.reason = accountHistory.getReason();
        this.id = accountHistory.getId();
        this.createDate = accountHistory.getCreateDate();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public AccountHistoryReason getReason() {
        return reason;
    }

    public void setReason(AccountHistoryReason reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
