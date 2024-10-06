package com.example.hotcode_bankingsolution.entity;

import com.example.hotcode_bankingsolution.dto.TransactionDTO;
import com.example.hotcode_bankingsolution.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String accountNumber;
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;

    public Transaction(TransactionDTO transactionDTO) {
        this.transactionType = transactionDTO.transactionType();
        this.accountNumber = transactionDTO.accountNumber();
        this.fromAccountNumber = transactionDTO.fromAccountNumber();
        this.toAccountNumber = transactionDTO.toAccountNumber();
        this.amount = transactionDTO.amount();
    }
}
