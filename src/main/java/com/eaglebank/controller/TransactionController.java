package com.eaglebank.controller;

import com.eaglebank.dto.CreateTransactionRequest;
import com.eaglebank.dto.TransactionResponse;
import com.eaglebank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts/{accountNumber}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Create a new transaction (deposit or withdrawal)
     */
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
            @PathVariable Long accountNumber,
            @RequestBody CreateTransactionRequest request) {
        TransactionResponse response = transactionService.createTransaction(accountNumber, request);
        return ResponseEntity.status(201).body(response);
    }

    /**
     * List all transactions for a given account
     */
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> listTransactions(
            @PathVariable Long accountNumber) {
        List<TransactionResponse> transactions = transactionService.getTransactions(accountNumber);
        return ResponseEntity.ok(transactions);
    }

    /**
     * Get a specific transaction by ID for a given account
     */
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(
            @PathVariable Long accountNumber,
            @PathVariable Long transactionId) {
        TransactionResponse transaction = transactionService.getTransaction(accountNumber, transactionId);
        return ResponseEntity.ok(transaction);
    }
}
