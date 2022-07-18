package com.ha.back.controllers.account;

import com.ha.back.models.account.SpendMoneyModel;
import com.ha.back.payload.request.account.CreateSpendMoneyRequest;
import com.ha.back.payload.request.account.SpendMoney;
import com.ha.back.service.account.SpendMoneyService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account/spend")
@AllArgsConstructor
public class SpendMoneyController {

    private SpendMoneyService spendMoneyService;

    @PostMapping("/create")
    public ResponseEntity<?> createSpendMoney(@Valid @RequestBody CreateSpendMoneyRequest createSpendMoneyRequest, Principal principal) {
        System.out.println(createSpendMoneyRequest);
        SpendMoneyModel spendMoney = spendMoneyService.save(createSpendMoneyRequest.getSpendMoney());
        return ResponseEntity.ok(spendMoney);
    }

}


