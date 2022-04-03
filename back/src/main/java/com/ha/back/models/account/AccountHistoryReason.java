package com.ha.back.models.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "account_history_reason")
public class AccountHistoryReason {
    @Id
    @Column(name = "ac_h_r")
    private String acHR = UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    public String getAcHR() {
        return acHR;
    }

    public void setAcHR(String acHR) {
        this.acHR = acHR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
