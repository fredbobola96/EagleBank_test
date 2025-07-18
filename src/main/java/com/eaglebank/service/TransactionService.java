package com.eaglebank.service;

import com.eaglebank.pojo.CreateTransactionRequest;
import com.eaglebank.pojo.TransactionResponse;

import java.util.List;

public interface TransactionService {

    /**
     * Creates a deposit or withdrawal transaction for a given bank account.
     *
     * @param accountNumber the account to operate on
     * @param request       the transaction request DTO
     * @return the created transaction response
     */
    TransactionResponse createTransaction(Long accountNumber, CreateTransactionRequest request);
    List<TransactionResponse> getTransactions(Long accountNumber);

    /**
     * Retrieves a specific transaction by ID for a given account.
     *
     * @param accountNumber the account number
     * @param transactionId the ID of the transaction
     * @return transaction response
     */
    TransactionResponse getTransaction(Long accountNumber, Long transactionId);
}
