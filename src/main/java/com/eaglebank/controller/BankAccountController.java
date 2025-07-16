package com.eaglebank.controller;

import com.eaglebank.dto.*;
import com.eaglebank.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> createAccount(@RequestBody CreateBankAccountRequest request) {
        return ResponseEntity.status(201).body(bankAccountService.createAccount(request));
    }

    @GetMapping
    public ResponseEntity<List<BankAccountResponse>> listAccounts() {
        return ResponseEntity.ok(bankAccountService.listAccounts());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccountResponse> getAccount(@PathVariable Long accountNumber) {
        return ResponseEntity.ok(bankAccountService.getAccount(accountNumber));
    }

    @PatchMapping("/{accountNumber}")
    public ResponseEntity<BankAccountResponse> updateAccount(@PathVariable Long accountNumber, @RequestBody UpdateBankAccountRequest request) {
        return ResponseEntity.ok(bankAccountService.updateBankAccount(accountNumber, request));
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountNumber) {
        bankAccountService.deleteAccount(accountNumber);
        return ResponseEntity.noContent().build();
    }
}
