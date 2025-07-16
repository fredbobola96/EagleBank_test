package com.eaglebank.controller;

import com.eaglebank.model.BankAccount;
import com.eaglebank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    @Autowired
    private BankAccountService accountService;

    @PostMapping("/client/{clientId}")
    public BankAccount createAccount(@PathVariable Long clientId, @RequestBody BankAccount account) {
        return accountService.createBankAccount(clientId, account);
    }

    @GetMapping("/client/{clientId}")
    public List<BankAccount> getAccountsByClient(@PathVariable Long clientId) {
        return accountService.getBankAccountsByClientId(clientId);
    }

    @GetMapping("/{id}")
    public BankAccount getAccountById(@PathVariable Long id) {
        return accountService.getBankAccountById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public BankAccount updateAccount(@PathVariable Long id, @RequestBody BankAccount account) {
        return accountService.updateBankAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteBankAccount(id);
    }
}
