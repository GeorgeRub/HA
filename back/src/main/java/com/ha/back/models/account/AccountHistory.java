package com.ha.back.models.account;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "account_history")
public class AccountHistory {

    @Id
    @Column(name = "ach_id")
    private String id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ac_id", nullable = false)
    @NotNull
    private Account accountId;

    @Column(name = "value", nullable = false)
    @NotNull
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ac_h_r", nullable = false)
    @NotNull
    private AccountHistoryReason reason;

    @Column(name = "balance", nullable = false)
    @NotNull
    private Double balance = 0.0;

    @Column(name = "creat_date", nullable = false)
    @NotNull
    private Date createDate = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pp_id")
    private PaymentPlace paymentPlace;

    public AccountHistory() {
    }

    public AccountHistory(Account accountId, Double value) {
        this.accountId = accountId;
        this.balance = accountId.getBalance();
        this.value = value;
    }

    public AccountHistory(Account accountId, Double value, AccountHistoryReason reason) {
        this.accountId = accountId;
        this.balance = accountId.getBalance();
        this.value = value;
        this.reason = reason;
    }

    public AccountHistory(Account accountId, Double value, AccountHistoryReason reason, PaymentPlace paymentPlace) {
        this.accountId = accountId;
        this.balance = accountId.getBalance();
        this.value = value;
        this.reason = reason;
        this.paymentPlace = paymentPlace;
    }

    public PaymentPlace getPaymentPlace() {
        return paymentPlace;
    }

    public void setPaymentPlace(PaymentPlace paymentPlace) {
        this.paymentPlace = paymentPlace;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
