package com.ha.back.payload.request.account;

import lombok.*;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateSpendMoneyRequest{
    private SpendMoney spendMoney;
}
