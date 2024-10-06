package com.example.hotcode_bankingsolution.controller;

import com.example.hotcode_bankingsolution.dto.AccountDTO;
import com.example.hotcode_bankingsolution.entity.Account;
import com.example.hotcode_bankingsolution.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.hotcode_bankingsolution.entity.Account.mapToAccountDTOsList;
import static com.example.hotcode_bankingsolution.entity.Account.newAccount;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createNewAccount")
    public AccountDTO createNewAccount(@RequestBody AccountDTO accountDTO) {
        var account = newAccount(accountDTO);
        var savedAccount = this.accountService.saveAccount(account);
        return new AccountDTO(savedAccount);
    }

    @PostMapping("/update/{accountNumber}")
    public AccountDTO update(@PathVariable String accountNumber, @RequestBody AccountDTO accountDTO) {
        var account = new Account(accountDTO);
        var updatedAccount = this.accountService.updateAccount(accountNumber, account);
        return new AccountDTO(updatedAccount);
    }

    @PostMapping("/delete/{accountNumber}")
    public void delete(@PathVariable String accountNumber) {
        this.accountService.deleteAccount(accountNumber);
    }

    @GetMapping("/{accountNumber}")
    public AccountDTO getAccountByAccountNumber(@PathVariable String accountNumber) {
        var account = this.accountService.getAccountByAccountNumber(accountNumber);
        return new AccountDTO(account);
    }

    @GetMapping("/getAllAccounts")
    public List<AccountDTO> getAllAccounts() {
        var allAccounts = this.accountService.getAllAccounts();
        return mapToAccountDTOsList(allAccounts);
    }
}
