package com.eaglebank.service;

import com.eaglebank.pojo.*;

import java.util.List;

public interface BankAccountService {
    BankAccountResponse createAccount(CreateBankAccountRequest request);
    List<BankAccountResponse> listAccounts();
    BankAccountResponse getAccount(Long accountNumber);
    BankAccountResponse updateBankAccount(Long accountNumber, UpdateBankAccountRequest request);
    void deleteAccount(Long accountNumber);
}
