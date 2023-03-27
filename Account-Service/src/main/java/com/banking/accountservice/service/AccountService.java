package com.banking.accountservice.service;

import com.banking.accountservice.entity.Account;
import com.banking.accountservice.repository.AccountRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final KafkaTemplate<String, Account> kafkaTemplate;

    public AccountService(AccountRepository accountRepository, KafkaTemplate<String, Account> kafkaTemplate) {
        this.accountRepository = accountRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Account createAccount(Account account) {
        account = accountRepository.save(account);
        //kafkaTemplate.send("account-created", account);
        return account;
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAllActiveAccounts() {
        return accountRepository.findAllByIsActive(true);
    }

    public Account updateAccountBalance(Long id, BigDecimal balance) {
        Account account = getAccountById(id);
        account.setBalance(balance);
        account = accountRepository.save(account);
        //kafkaTemplate.send("balance-updated", account);
        return account;
    }

}
