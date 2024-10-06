package com.example.hotcode_bankingsolution.entity;

import com.example.hotcode_bankingsolution.dto.AccountDTO;
import com.example.hotcode_bankingsolution.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.example.hotcode_bankingsolution.enums.Currency.USD;
import static java.math.BigDecimal.ZERO;
import static java.util.Objects.nonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String ownerName;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private LocalDateTime createdAt;

    public Account(
            String accountNumber,
            String ownerName,
            BigDecimal balance,
            Currency currency,
            LocalDateTime createdAt
    ) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.currency = currency;
        this.createdAt = createdAt;
    }

    public Account(AccountDTO accountDTO) {
        this.accountNumber = accountDTO.accountNumber();
        this.ownerName = accountDTO.ownerName();
        this.balance = accountDTO.balance();
        this.currency = accountDTO.currency();
        this.createdAt = accountDTO.createdAt();
    }

    public static Account newAccount(AccountDTO accountDTO) {
        return new Account(
                UUID.randomUUID().toString(),
                accountDTO.ownerName(),
                ZERO,
                USD,
                LocalDateTime.now()
        );
    }

    public static List<AccountDTO> mapToAccountDTOsList(Collection<Account> accountDTOs) {
        return accountDTOs.stream()
                .map(AccountDTO::new)
                .toList();
    }

    public Account update(Account account) {
        this.id = nonNull(account.getId()) ? account.getId() : this.id;
        this.accountNumber = nonNull(account.getAccountNumber()) ? account.getAccountNumber() : this.accountNumber;
        this.ownerName = nonNull(account.getOwnerName()) ? account.getOwnerName() : this.ownerName;
        this.balance = nonNull(account.getBalance()) ? account.getBalance() : this.balance;
        this.currency = nonNull(account.getCurrency()) ? account.getCurrency() : this.currency;
        this.createdAt = nonNull(account.getCreatedAt()) ? account.getCreatedAt() : this.createdAt;
        return this;
    }
}
