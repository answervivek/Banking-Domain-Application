package com.deactivateAccount.service;

import com.deactivateAccount.entity.Account;
import com.deactivateAccount.repository.AccountRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final KafkaTemplate<String, Account> kafkaTemplate;

    public AccountService(AccountRepository accountRepository, KafkaTemplate<String, Account> kafkaTemplate) {
        this.accountRepository = accountRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean deactivateAccount(Long accountId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setIsActive(false);
            accountRepository.save(account);
            return true;
        } else {
            return false;
        }
    }
}
