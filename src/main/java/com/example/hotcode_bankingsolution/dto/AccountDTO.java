package com.example.hotcode_bankingsolution.dto;

import com.example.hotcode_bankingsolution.entity.Account;
import com.example.hotcode_bankingsolution.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDTO(
        Long id,
        String accountNumber,
        String ownerName,
        BigDecimal balance,
        Currency currency,
        LocalDateTime createdAt
) {

    public AccountDTO(Account account) {
        this(
                account.getId(),
                account.getAccountNumber(),
                account.getOwnerName(),
                account.getBalance(),
                account.getCurrency(),
                account.getCreatedAt()
        );
    }
}
