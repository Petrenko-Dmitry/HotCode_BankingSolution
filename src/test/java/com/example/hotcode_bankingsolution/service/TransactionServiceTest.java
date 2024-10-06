package com.example.hotcode_bankingsolution.service;

import com.example.hotcode_bankingsolution.entity.Account;
import com.example.hotcode_bankingsolution.entity.Transaction;
import com.example.hotcode_bankingsolution.exception.NotFoundException;
import com.example.hotcode_bankingsolution.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.example.hotcode_bankingsolution.constants.StringConstants.ACCOUNT_NOT_FOUND_FORMAT;
import static com.example.hotcode_bankingsolution.constants.StringConstants.TRANSACTION_NOT_FOUND_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    private TransactionRepository transactionRepository;
    private TransactionService componentUnderTest;

    @BeforeEach
    void setUp() {
        this.transactionRepository = mock(TransactionRepository.class);
        this.componentUnderTest = new TransactionService(this.transactionRepository);
    }

    @Test
    void saveTransactionTest() {
        // Arrange
        var transaction = mock(Transaction.class);

        // Act
        this.componentUnderTest.saveTransaction(transaction);

        // Assert
        verify(transactionRepository).save(transaction);
    }

    @Test
    void getTransactionByIdThrowExceptionTest() {
        // Arrange
        var id = 1L;
        when(this.transactionRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        var actual = assertThatThrownBy(() -> this.componentUnderTest.getTransaction(id));

        // Assert
        verify(this.transactionRepository).findById(id);
        actual.isInstanceOf(NotFoundException.class)
                .hasMessage(String.format(TRANSACTION_NOT_FOUND_FORMAT, id));

    }

    @Test
    void getTransactionByIdTest() {
        // Arrange
        var id = 1L;
        var transaction = mock(Transaction.class);
        when(this.transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        // Act
        var actual = this.componentUnderTest.getTransaction(id);

        // Assert
        assertThat(actual).isEqualTo(transaction);
        verify(this.transactionRepository).findById(id);
    }

    @Test
    void deleteTransactionTest() {
        // Arrange
        var id = 1L;
        var transaction = mock(Transaction.class);
        when(this.transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        // Act
        this.componentUnderTest.deleteTransaction(id);

        // Assert
        verify(this.transactionRepository).findById(id);
        verify(this.transactionRepository).delete(transaction);
    }
}
