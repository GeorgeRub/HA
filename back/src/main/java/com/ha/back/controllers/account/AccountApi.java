package com.ha.back.controllers.account;

import com.ha.back.exceptions.NotFountAnyUser;
import com.ha.back.exceptions.currency.NotFoundCurrency;
import com.ha.back.models.account.Account;
import com.ha.back.models.account.currency.Currency;
import com.ha.back.models.user.User;
import com.ha.back.payload.request.account.CreateAccountRequest;
import com.ha.back.payload.response.MessageResponse;
import com.ha.back.service.UserService;
import com.ha.back.service.account.AccountHistoryService;
import com.ha.back.service.account.AccountService;
import com.ha.back.service.account.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountApi {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountHistoryService accountHistoryService;

    @Autowired
    CurrencyService currencyService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> accountList(Principal principal) {
        try {
            User user = userService.getByName(principal.getName());
            return ResponseEntity.ok(accountService.findAllByUser(user));
        } catch (NotFountAnyUser e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(e.getLocalizedMessage()));
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountRequest accountRequest, Principal principal) {
        System.out.println("create");
        try {
            User user = userService.getByName(principal.getName());
            if (accountService.exist(user, accountRequest.getName())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: This use has account with name " + accountRequest.getName() + "!"));
            }
            Currency currency = currencyService.findByName(accountRequest.getCurrency());
            Account account = accountService.save(new Account(user, accountRequest, currency));
            return ResponseEntity.ok(account);
        } catch (NotFountAnyUser | NotFoundCurrency e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(e.getLocalizedMessage()));
        }
    }
}
