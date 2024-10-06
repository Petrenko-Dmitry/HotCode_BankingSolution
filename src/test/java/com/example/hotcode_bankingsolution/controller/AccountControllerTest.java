package com.example.hotcode_bankingsolution.controller;

import com.example.hotcode_bankingsolution.dto.AccountDTO;
import com.example.hotcode_bankingsolution.entity.Account;
import com.example.hotcode_bankingsolution.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.hotcode_bankingsolution.entity.Account.mapToAccountDTOsList;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    private AccountService accountService;
    private AccountController componentUnderTest;

    @BeforeEach
    void setUp() {
        this.accountService = mock(AccountService.class);
        this.componentUnderTest = new AccountController(this.accountService);
    }

    @Test
    void createNewAccountTest() {
        // Arrange
        var accountDTO = mock(AccountDTO.class);
        var account = new Account(accountDTO);
        when(this.accountService.saveAccount(any())).thenReturn(account);
        var expected = new AccountDTO(account);

        // Act
        var actual = this.componentUnderTest.createNewAccount(accountDTO);

        // Assert
        assertThat(actual).isEqualTo(expected);
        verify(this.accountService).saveAccount(any());
    }

    @Test
    void updateTest() {
        // Arrange
        var accountNumber = EMPTY;
        var accountDTO = mock(AccountDTO.class);
        var account = new Account(accountDTO);
        when(this.accountService.updateAccount(accountNumber, account)).thenReturn(account);
        var expected = new AccountDTO(account);

        // Act
        var actual = this.componentUnderTest.update(accountNumber, accountDTO);

        // Assert
        assertThat(actual).isEqualTo(expected);
        verify(this.accountService).updateAccount(accountNumber, account);
    }

    @Test
    void deleteTest() {
        // Arrange
        var accountNumber = EMPTY;

        // Act
        this.componentUnderTest.delete(accountNumber);

        // Assert
        verify(this.accountService).deleteAccount(accountNumber);
    }

    @Test
    void getAccountByAccountNumberTest() {
        // Arrange
        var accountNumber = EMPTY;
        var account = mock(Account.class);
        when(this.accountService.getAccountByAccountNumber(accountNumber)).thenReturn(account);
        var expected = new AccountDTO(account);

        // Act
        var actual = this.componentUnderTest.getAccountByAccountNumber(accountNumber);

        // Assert
        assertThat(actual).isEqualTo(expected);
        verify(this.accountService).getAccountByAccountNumber(accountNumber);
    }

    @Test
    void getAllAccountsTest() {
        // Arrange
        var account1 = mock(Account.class);
        var account2 = mock(Account.class);
        var accounts = List.of(account1, account2);
        when(this.accountService.getAllAccounts()).thenReturn(accounts);
        var expected = mapToAccountDTOsList(accounts);

        // Act
        var actual = this.componentUnderTest.getAllAccounts();

        // Assert
        assertThat(actual).isEqualTo(expected);
        verify(this.accountService).getAllAccounts();
    }
}
