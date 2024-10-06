package com.example.hotcode_bankingsolution.service;

import com.example.hotcode_bankingsolution.entity.Account;
import com.example.hotcode_bankingsolution.exception.NotFoundException;
import com.example.hotcode_bankingsolution.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.example.hotcode_bankingsolution.constants.StringConstants.ACCOUNT_NOT_FOUND_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private AccountRepository accountRepository;
    private AccountService componentUnderTest;

    @BeforeEach
    void setUp() {
        this.accountRepository = mock(AccountRepository.class);
        this.componentUnderTest = new AccountService(this.accountRepository);
    }

    @Test
    void saveAccountTest() {
        // Arrange
        var account = mock(Account.class);

        // Act
        this.componentUnderTest.saveAccount(account);

        // Assert
        verify(this.accountRepository).save(account);
    }

    @Test
    void saveAccountsTest() {
        // Arrange
        var account = mock(Account.class);
        var accountsToSave = List.of(account);

        // Act
        this.componentUnderTest.saveAccounts(accountsToSave);

        // Assert
        verify(this.accountRepository).saveAll(accountsToSave);
    }

    @Test
    void updateAccountTest() {
        // Arrange
        var accountNumber = "899a5f07-338f-4fc3-aec0-f3876cd196f5";
        var accountForUpdate = new Account();
        var account = new Account();
        when(this.accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(accountForUpdate));
        var accountToSave = accountForUpdate.update(account);
        when(this.accountRepository.save(accountToSave)).thenReturn(accountToSave);

        // Act
        var actual = this.componentUnderTest.updateAccount(accountNumber, account);

        // Assert
        assertThat(actual).isEqualTo(accountToSave);
        verify(this.accountRepository).findByAccountNumber(accountNumber);
        verify(this.accountRepository).save(accountToSave);
    }

    @Test
    void deleteAccountTest() {
        // Arrange
        var accountNumber = "899a5f07-338f-4fc3-aec0-f3876cd196f5";
        var account = new Account();
        when(this.accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        // Act
        this.componentUnderTest.deleteAccount(accountNumber);

        // Assert
        verify(this.accountRepository).findByAccountNumber(accountNumber);
        verify(this.accountRepository).delete(account);
    }

    @Test
    void getAccountByAccountNumberThrowExceptionTest() {
        // Arrange
        var accountNumber = "899a5f07-338f-4fc3-aec0-f3876cd196f5";
        when(this.accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        // Act
        var actual = assertThatThrownBy(() -> this.componentUnderTest.getAccountByAccountNumber(accountNumber));

        // Assert
        verify(this.accountRepository).findByAccountNumber(accountNumber);
        actual.isInstanceOf(NotFoundException.class)
                .hasMessage(String.format(ACCOUNT_NOT_FOUND_FORMAT, accountNumber));

    }

    @Test
    void getAccountByAccountNumberTest() {
        // Arrange
        var accountNumber = "899a5f07-338f-4fc3-aec0-f3876cd196f5";
        var account = mock(Account.class);
        when(this.accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        // Act
        var actual = this.componentUnderTest.getAccountByAccountNumber(accountNumber);

        // Assert
        assertThat(actual).isEqualTo(account);
        verify(this.accountRepository).findByAccountNumber(accountNumber);
    }

    @Test
    void getAllAccountsTest() {
        // Act
        this.componentUnderTest.getAllAccounts();

        // Assert
        verify(this.accountRepository).findAll();
    }
}
