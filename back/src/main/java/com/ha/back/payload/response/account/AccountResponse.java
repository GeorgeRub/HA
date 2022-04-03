package com.ha.back.payload.response.account;

import com.ha.back.models.account.Account;
import com.ha.back.models.account.currency.Currency;

import java.util.Date;

public class AccountResponse {
    private String ac_id = "";
    private Currency currency;
    private String accountName;
    private Double balance = 0.0;
    private Date dateCreated = new Date();
    private Date dateChanged;

    public AccountResponse() {
    }
    public AccountResponse(Account account) {
        this.ac_id = account.getAc_id();
        this.currency = account.getCurrency();
        this.accountName = account.getAccountName();
        this.balance = account.getBalance();
        this.dateCreated = account.getDateCreated();
        this.dateChanged = account.getDateChanged();
    }

    public String getAc_id() {
        return ac_id;
    }

    public void setAc_id(String ac_id) {
        this.ac_id = ac_id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Date dateChanged) {
        this.dateChanged = dateChanged;
    }
}
