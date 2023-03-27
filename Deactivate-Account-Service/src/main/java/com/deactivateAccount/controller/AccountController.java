package com.deactivateAccount.controller;

import com.deactivateAccount.entity.Account;
import com.deactivateAccount.service.AccountService;
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
    @PostMapping("/{accountId}/deactivate")
    public ResponseEntity<String> deactivateAccount(@PathVariable Long accountId) {
        boolean isDeactivated = accountService.deactivateAccount(accountId);
        if (isDeactivated) {
            return ResponseEntity.ok("Account with ID " + accountId + " has been deactivated.");
        } else {
            return ResponseEntity.ok("Account with ID " + accountId + " not found.");
        }
    }
}