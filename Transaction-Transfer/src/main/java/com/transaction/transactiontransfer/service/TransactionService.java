package com.transaction.transactiontransfer.service;

import com.banking.accountservice.entity.Account;
import com.transaction.transactiontransfer.constant.TransactionType;
import com.transaction.transactiontransfer.entity.Transaction;
import com.transaction.transactiontransfer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.banking.accountservice.service.AccountService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
  //  @Autowired
    private final AccountService accountService;
    //private final KafkaTemplate<String, Notification> kafkaTemplate;


    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public Transaction createDepositTransaction(Long accountId, BigDecimal amount) {
        Account account = accountService.getAccountById(accountId);
        BigDecimal newBalance = account.getBalance().add(amount);
        accountService.updateAccountBalance(accountId, newBalance);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setSourceAccountId(accountId);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public Transaction createWithdrawalTransaction(Long accountId, BigDecimal amount) {
        Account account = accountService.getAccountById(accountId);
        BigDecimal newBalance = account.getBalance().subtract(amount);
        accountService.updateAccountBalance(accountId, newBalance);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setAmount(amount);
        transaction.setSourceAccountId(accountId);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public Transaction createTransferTransaction(Long sourceAccountId, Long destinationAccountId, BigDecimal amount) {
        Account sourceAccount = accountService.getAccountById(sourceAccountId);
        Account destinationAccount = accountService.getAccountById(destinationAccountId);

        BigDecimal newSourceBalance = sourceAccount.getBalance().subtract(amount);
        BigDecimal newDestinationBalance = destinationAccount.getBalance().add(amount);

        accountService.updateAccountBalance(sourceAccountId, newSourceBalance);
        accountService.updateAccountBalance(destinationAccountId, newDestinationBalance);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.TRANSFER);
        transaction.setAmount(amount);
        transaction.setSourceAccountId(sourceAccountId);
        transaction.setDestinationAccountId(destinationAccountId);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

//    @KafkaListener(topics = "account-created", groupId = "transaction-group")
//    public void handleAccountCreated(Account account) {
//        Notification notification = new Notification();
//        notification.setType(NotificationType.EMAIL);
//        notification.setMessage("Account created for customer " + account.getCustomerName());
//        notification.setRecipient(account.getCustomerName());
//
//        kafkaTemplate.send("notification", notification);
//    }
//
//    @KafkaListener(topics = "balance-updated", groupId = "transaction-group")
//    public void handleBalanceUpdated(Account account) {
//        Notification notification = new Notification();
//        notification.setType(NotificationType.SMS);
//        notification.setMessage("Balance updated for account " + account.getId());
//        notification.setRecipient(account.getCustomerName());
//
//        kafkaTemplate.send("notification", notification);
//    }
}

