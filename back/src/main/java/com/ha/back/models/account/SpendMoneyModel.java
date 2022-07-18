package com.ha.back.models.account;

import com.ha.back.models.account.currency.Currency;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "spend_money")
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SpendMoneyModel {

    @Id
    @Column(name = "sp_id")
    private String id = UUID.randomUUID().toString();
    private Date date;
    private String spendName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_name", insertable = false, updatable = false)
    private Currency currency;
    private BigDecimal totalPrice;
    private BigDecimal exchangePrice;
    private BigDecimal totalPriceInCurrency;
    private boolean differentCurrency;

}
