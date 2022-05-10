package com.ha.back.controllers.account;

import com.ha.back.exceptions.NotFountAnyUser;
import com.ha.back.exceptions.account.NoFoundAnyAccount;
import com.ha.back.exceptions.currency.NotFoundCurrency;
import com.ha.back.models.account.Account;
import com.ha.back.models.account.AccountHistory;
import com.ha.back.models.account.AccountHistoryReason;
import com.ha.back.models.account.currency.Currency;
import com.ha.back.models.user.User;
import com.ha.back.payload.request.account.CreateAccountRequest;
import com.ha.back.payload.response.MessageResponse;
import com.ha.back.payload.response.account.AccountHistoryByIdAndDateResponse;
import com.ha.back.payload.response.account.AccountResponse;
import com.ha.back.service.UserService;
import com.ha.back.service.account.AccountHistoryReasonService;
import com.ha.back.service.account.AccountHistoryService;
import com.ha.back.service.account.AccountService;
import com.ha.back.service.account.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    AccountHistoryReasonService historyReasonService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> accountList(Principal principal)throws NotFountAnyUser {
//        try {
            User user = userService.getByName(principal.getName());
            return ResponseEntity.ok(accountService.findAllByUser(user));
//        } catch (NotFountAnyUser e) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body(new MessageResponse(e.getLocalizedMessage()));
//        }
    }

    @GetMapping("/history/id/{id}/startDate/{startDate}/endDate/{endDate}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> historyByDate(@PathVariable String id, @PathVariable Date endDate, @PathVariable Date startDate)throws NotFountAnyUser {
        List<AccountHistory> accountList = accountHistoryService.findByIdAndBetweenDates(id, startDate, endDate);
        if (accountList != null && !accountList.isEmpty()) {
            List<AccountHistoryByIdAndDateResponse> history = new ArrayList<>();
            accountList.forEach(accountHistory -> history.add(new AccountHistoryByIdAndDateResponse(accountHistory)));
            return ResponseEntity.ok(history);
        }
        throw new NotFountAnyUser("no account history");
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("no account history"));
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getById(Principal principal, @PathVariable String id) throws NotFountAnyUser, NoFoundAnyAccount {
        User user = userService.getByName(principal.getName());
        Account account = accountService.findByAc_idAndUser_Id(id, user);
        if (account == null) {
            throw new NoFoundAnyAccount("We could not find any account with id " + id + " !");
        }
        return ResponseEntity.ok(new AccountResponse(account));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountRequest accountRequest, Principal principal) throws NotFountAnyUser, NotFoundCurrency {
//        try {
            User user = userService.getByName(principal.getName());
            if (accountService.exist(user, accountRequest.getName())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: This use has account with name " + accountRequest.getName() + "!"));
            }
            Currency currency = currencyService.findByName(accountRequest.getCurrency());
            Account account = accountService.save(new Account(user, accountRequest, currency));
            AccountHistoryReason reason = historyReasonService.findByNameIgnoreCase("Создание");
            if (reason == null) {
                reason = new AccountHistoryReason();
                reason.setName("Создание");
                reason = historyReasonService.save(reason);
            }
            AccountHistory accountHistory = new AccountHistory(account, account.getBalance(), reason);
            accountHistoryService.save(accountHistory);
            return ResponseEntity.ok(account);
//        } catch (NotFountAnyUser | NotFoundCurrency e) {
//            e.printStackTrace();
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse(e.getLocalizedMessage()));
//        }
    }
}
