package com.ha.back.payload.request.account;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SpendMoney {

    private Date date;
    private String spendName;
    private String currency;
    private BigDecimal totalPrice;
    private BigDecimal exchangePrice;
    private BigDecimal totalPriceInCurrency;
    private boolean differentCurrency;

}
