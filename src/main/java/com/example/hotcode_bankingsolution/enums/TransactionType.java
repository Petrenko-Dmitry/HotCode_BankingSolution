package com.example.hotcode_bankingsolution.enums;

import com.example.hotcode_bankingsolution.dto.TransactionDTO;
import com.example.hotcode_bankingsolution.entity.Account;

import java.util.List;
import java.util.stream.Collectors;

public enum TransactionType {
    DEPOSIT {
        @Override
        public List<Account> process(List<Account> accounts, TransactionDTO transactionDTO) {
            return accounts.stream()
                    .filter(account -> account.getAccountNumber().equals(transactionDTO.accountNumber()))
                    .peek(account -> account.setBalance(account.getBalance().add(transactionDTO.amount())))
                    .collect(Collectors.toList());
        }
    },
    WITHDRAWAL {
        @Override
        public List<Account> process(List<Account> accounts, TransactionDTO transactionDTO) {
            return accounts.stream()
                    .filter(account -> account.getAccountNumber().equals(transactionDTO.accountNumber()))
                    .peek(account -> account.setBalance(account.getBalance().subtract(transactionDTO.amount())))
                    .collect(Collectors.toList());
        }
    },
    TRANSFER {
        @Override
        public List<Account> process(List<Account> accounts, TransactionDTO transactionDTO) {
            return accounts.stream()
                    .filter(account -> transactionDTO.isAccountNumberEqualsFromOrToAccountNumber(account.getAccountNumber()))
                    .peek(account -> {
                        if (transactionDTO.isAccountNumberEqualsToAccountNumber(account.getAccountNumber())) {
                            account.setBalance(account.getBalance().add(transactionDTO.amount()));
                        } else {
                            account.setBalance(account.getBalance().subtract(transactionDTO.amount()));
                        }
                    })
                    .collect(Collectors.toList());
        }
    };

    public abstract List<Account> process(List<Account> accounts, TransactionDTO transactionDTO);
}
