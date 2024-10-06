package com.example.hotcode_bankingsolution.service;

import com.example.hotcode_bankingsolution.entity.Account;
import com.example.hotcode_bankingsolution.exception.NotFoundException;
import com.example.hotcode_bankingsolution.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.example.hotcode_bankingsolution.constants.StringConstants.ACCOUNT_NOT_FOUND_FORMAT;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccount(Account account) {
        return this.accountRepository.save(account);
    }

    public void saveAccounts(Collection<Account> accounts) {
        this.accountRepository.saveAll(accounts);
    }

    public Account updateAccount(String accountNumber, Account account) {
        var accountByAccountNumber = this.getAccountByAccountNumber(accountNumber);
        var accountToSave = accountByAccountNumber.update(account);
        return this.accountRepository.save(accountToSave);
    }

    public void deleteAccount(String accountNumber) {
        var account = this.getAccountByAccountNumber(accountNumber);
        this.accountRepository.delete(account);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return this.accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException(String.format(ACCOUNT_NOT_FOUND_FORMAT, accountNumber)));
    }

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }
}
