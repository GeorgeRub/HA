package com.ha.back.controllers.account;

import com.ha.back.exceptions.NotFountAnyUser;
import com.ha.back.exceptions.account.NotFoundException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/account")
public class AccountApi {

    final UserService userService;

    final AccountService accountService;

    final AccountHistoryService accountHistoryService;

    final CurrencyService currencyService;

    final AccountHistoryReasonService historyReasonService;

    public AccountApi(UserService userService,
                      AccountService accountService,
                      AccountHistoryService accountHistoryService,
                      CurrencyService currencyService,
                      AccountHistoryReasonService historyReasonService) {
        this.userService = userService;
        this.accountService = accountService;
        this.accountHistoryService = accountHistoryService;
        this.currencyService = currencyService;
        this.historyReasonService = historyReasonService;
    }

    @GetMapping(value = "/all", produces = {"application/json", "application/xml"})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> accountList(Principal principal) throws NotFountAnyUser {
        User user = userService.getByName(principal.getName());
        if (user == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not find");
        List<Account> accountList = accountService.findAllByUser(user);
        return ResponseEntity.ok(accountList);
    }

    @GetMapping("/history/id/{id}/startDate/{startDate}/endDate/{endDate}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> historyByDate(@PathVariable String id, @PathVariable Date endDate, @PathVariable Date startDate) {
        List<AccountHistoryByIdAndDateResponse> history = new ArrayList<>();
        List<AccountHistory> accountList = accountHistoryService.findByIdAndBetweenDates(id, startDate, endDate);
        if (accountList != null && !accountList.isEmpty()) {
            accountList.forEach(accountHistory -> history.add(new AccountHistoryByIdAndDateResponse(accountHistory)));
        }
        return ResponseEntity.ok(history);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getById(Principal principal, @PathVariable String id) {
        User user = userService.getByName(principal.getName());
        Account account = accountService.findByAc_idAndUser_Id(id, user);
        if (account == null) {
            throw new NotFoundException("We could not find any account with id " + id + " !");
        }
        return ResponseEntity.ok(new AccountResponse(account));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountRequest accountRequest, Principal principal) {
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
    }
}
