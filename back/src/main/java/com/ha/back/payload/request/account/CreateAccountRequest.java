package com.ha.back.payload.request.account;

import javax.validation.constraints.NotBlank;

public class CreateAccountRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String currency;
    private Double balance = 0.0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
