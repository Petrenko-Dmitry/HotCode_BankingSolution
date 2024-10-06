package com.example.hotcode_bankingsolution.controller;

import com.example.hotcode_bankingsolution.dto.TransactionDTO;
import com.example.hotcode_bankingsolution.entity.Account;
import com.example.hotcode_bankingsolution.entity.Transaction;
import com.example.hotcode_bankingsolution.enums.TransactionType;
import com.example.hotcode_bankingsolution.service.AccountService;
import com.example.hotcode_bankingsolution.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.hotcode_bankingsolution.enums.Currency.USD;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    private AccountService accountService;
    private TransactionService transactionService;
    private TransactionController componentUnderTest;

    @BeforeEach
    void setUp() {
        this.accountService = mock(AccountService.class);
        this.transactionService = mock(TransactionService.class);
        this.componentUnderTest = new TransactionController(this.accountService, this.transactionService);
    }

    @Test
    void depositTest() {
        // Arrange
        var accountNumber = "cb9d0e01-c97d-457f-9dfd-ed9d19452357";
        var transactionDTO = new TransactionDTO(
                TransactionType.DEPOSIT,
                accountNumber,
                EMPTY,
                EMPTY,
                new BigDecimal("32")
        );
        var account = new Account(
                accountNumber,
                EMPTY,
                new BigDecimal("2"),
                USD,
                LocalDateTime.now()
        );
        when(this.accountService.getAccountByAccountNumber(transactionDTO.accountNumber())).thenReturn(account);
        var processedAccount = transactionDTO.transactionType().process(List.of(account), transactionDTO);
        var transactionToSave = new Transaction(transactionDTO);

        // Act
        this.componentUnderTest.deposit(transactionDTO);

        // Assert
        verify(this.accountService).getAccountByAccountNumber(transactionDTO.accountNumber());
        verify(this.accountService).saveAccounts(processedAccount);
        verify(this.transactionService).saveTransaction(transactionToSave);
    }

    @Test
    void withdrawTest() {
        // Arrange
        var accountNumber = "cb9d0e01-c97d-457f-9dfd-ed9d19452357";
        var transactionDTO = new TransactionDTO(
                TransactionType.WITHDRAWAL,
                accountNumber,
                EMPTY,
                EMPTY,
                new BigDecimal("32")
        );
        var account = new Account(
                accountNumber,
                EMPTY,
                new BigDecimal("50"),
                USD,
                LocalDateTime.now()
        );
        when(this.accountService.getAccountByAccountNumber(transactionDTO.accountNumber())).thenReturn(account);
        var processedAccount = transactionDTO.transactionType().process(List.of(account), transactionDTO);
        var transactionToSave = new Transaction(transactionDTO);

        // Act
        this.componentUnderTest.withdraw(transactionDTO);

        // Assert
        verify(this.accountService).getAccountByAccountNumber(transactionDTO.accountNumber());
        verify(this.accountService).saveAccounts(processedAccount);
        verify(this.transactionService).saveTransaction(transactionToSave);
    }

    @Test
    void transferTest() {
        // Arrange
        var accountNumberFrom = "cb9d0e01-c97d-457f-9dfd-ed9d19452357";
        var accountNumberTo = "2630b106-f6c1-428c-9a15-20abd0a5c6c6";
        var transactionDTO = new TransactionDTO(
                TransactionType.WITHDRAWAL,
                EMPTY,
                accountNumberFrom,
                accountNumberTo,
                new BigDecimal("32")
        );
        var accountFrom = new Account(
                accountNumberFrom,
                EMPTY,
                new BigDecimal("50"),
                USD,
                LocalDateTime.now()
        );
        var accountTo = new Account(
                accountNumberTo,
                EMPTY,
                new BigDecimal("50"),
                USD,
                LocalDateTime.now()
        );
        when(this.accountService.getAccountByAccountNumber(transactionDTO.fromAccountNumber())).thenReturn(accountFrom);
        when(this.accountService.getAccountByAccountNumber(transactionDTO.toAccountNumber())).thenReturn(accountTo);
        var processedAccount = transactionDTO.transactionType().process(List.of(accountFrom, accountTo), transactionDTO);
        var transactionToSave = new Transaction(transactionDTO);

        // Act
        this.componentUnderTest.transfer(transactionDTO);

        // Assert
        verify(this.accountService).getAccountByAccountNumber(transactionDTO.fromAccountNumber());
        verify(this.accountService).getAccountByAccountNumber(transactionDTO.toAccountNumber());
        verify(this.accountService).saveAccounts(processedAccount);
        verify(this.transactionService).saveTransaction(transactionToSave);
    }

    @Test
    void deleteTest() {
        // Arrange
        var id = 1L;

        // Act
        this.componentUnderTest.deleteTransaction(id);

        // Assert
        verify(this.transactionService).deleteTransaction(id);
    }
}
