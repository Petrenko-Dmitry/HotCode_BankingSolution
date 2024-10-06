package com.example.hotcode_bankingsolution.controller;

import com.example.hotcode_bankingsolution.dto.TransactionDTO;
import com.example.hotcode_bankingsolution.entity.Transaction;
import com.example.hotcode_bankingsolution.service.AccountService;
import com.example.hotcode_bankingsolution.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    public void deposit(@RequestBody TransactionDTO transactionDTO) {
        var account = this.accountService.getAccountByAccountNumber(transactionDTO.accountNumber());
        var processedAccount = transactionDTO.transactionType().process(List.of(account), transactionDTO);
        this.accountService.saveAccounts(processedAccount);
        this.transactionService.saveTransaction(new Transaction(transactionDTO));
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody TransactionDTO transactionDTO) {
        var account = this.accountService.getAccountByAccountNumber(transactionDTO.accountNumber());
        var processedAccount = transactionDTO.transactionType().process(List.of(account), transactionDTO);
        this.accountService.saveAccounts(processedAccount);
        this.transactionService.saveTransaction(new Transaction(transactionDTO));
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransactionDTO transactionDTO) {
        var accountFrom = this.accountService.getAccountByAccountNumber(transactionDTO.fromAccountNumber());
        var accountTo = this.accountService.getAccountByAccountNumber(transactionDTO.toAccountNumber());
        var processedAccount = transactionDTO.transactionType().process(List.of(accountFrom, accountTo), transactionDTO);
        this.accountService.saveAccounts(processedAccount);
        this.transactionService.saveTransaction(new Transaction(transactionDTO));
    }

    @PostMapping("/delete/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        this.transactionService.deleteTransaction(id);
    }
}
