package com.eaglebank.service;

import com.eaglebank.pojo.*;

import java.util.List;

public interface BankAccountService {

    /**
     * Creates an account.
     *
     * @param request the createBankAccount request POJO
     * @return the created bankAccount response
     */
    BankAccountResponse createAccount(CreateBankAccountRequest request);

    /**
     * Retrieves all accounts.
     *
     * @return all accounts
     */
    List<BankAccountResponse> listAccounts();

    /**
     * Retrieves a specific account by ID.
     *
     * @param accountNumber the ID of the account
     * @return bank account response
     */
    BankAccountResponse getAccount(Long accountNumber);

    /**
     * Updates bank account details by id
     * @param accountNumber the accountNumber of the account
     * @param request the UpdateBankAccountRequest request POJO
     * @return updated account
     */
    BankAccountResponse updateBankAccount(Long accountNumber, UpdateBankAccountRequest request);

    /**
     * Deletes bank account by id
     * @param accountNumber the ID of the bank account
     */
    void deleteAccount(Long accountNumber);
}
