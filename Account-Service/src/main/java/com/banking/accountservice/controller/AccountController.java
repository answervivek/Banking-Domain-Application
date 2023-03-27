package com.banking.accountservice.controller;

import com.banking.accountservice.entity.Account;
import com.banking.accountservice.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        account = accountService.createAccount(account);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Account>> getAllActiveAccounts() {
        List<Account> accounts = accountService.getAllActiveAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Account> updateAccountBalance(@PathVariable Long id, @RequestParam BigDecimal balance) {
        Account account = accountService.updateAccountBalance(id, balance);
        return ResponseEntity.ok(account);
    }
}

