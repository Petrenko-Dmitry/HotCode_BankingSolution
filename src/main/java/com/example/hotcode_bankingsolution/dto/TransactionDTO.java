package com.example.hotcode_bankingsolution.dto;

import com.example.hotcode_bankingsolution.enums.TransactionType;

import java.math.BigDecimal;

public record TransactionDTO(
        TransactionType transactionType,
        String accountNumber,
        String fromAccountNumber,
        String toAccountNumber,
        BigDecimal amount
) {

    public boolean isAccountNumberEqualsToAccountNumber(String accountNumber) {
        return this.toAccountNumber.equals(accountNumber);
    }

    public boolean isAccountNumberEqualsFromAccountNumber(String accountNumber) {
        return this.fromAccountNumber.equals(accountNumber);
    }

    public boolean isAccountNumberEqualsFromOrToAccountNumber(String accountNumber) {
        return this.isAccountNumberEqualsFromAccountNumber(accountNumber) || this.isAccountNumberEqualsToAccountNumber(accountNumber);
    }
}
