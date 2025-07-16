package com.eaglebank.service;

import com.eaglebank.dto.*;

import java.util.List;

public interface TransactionService {
    TransactionResponse createTransaction(Long accountNumber, CreateTransactionRequest request);
    List<TransactionResponse> getTransactions(Long accountNumber);
    TransactionResponse getTransaction(Long accountNumber, Long transactionId);
}
