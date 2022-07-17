package com.ha.back.models.account;

import com.ha.back.models.account.currency.Currency;
import com.ha.back.models.user.User;
import com.ha.back.payload.request.account.CreateAccountRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "account")
//@Table(name = "account",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = "ac_id")
//        })
public class Account {

    @Id
    @Column(name = "ac_id")
    private String ac_id = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name", nullable = false)
    @NotNull
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "account_name", nullable = false)
    @NotNull
    private String accountName;

    @Column(name = "balance")
    private Double balance = 0.0;

    @Column(name = "date_created")
    private Date dateCreated = new Date();

    @Column(name = "date_changed")
    private Date dateChanged;

    public Account() {
    }

    public Account(User user, CreateAccountRequest accountRequest, Currency currency) {
        this.user = user;
        this.accountName = accountRequest.getName();
        this.balance = accountRequest.getBalance();
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
}
