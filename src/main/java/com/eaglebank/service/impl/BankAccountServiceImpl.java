package com.eaglebank.service.impl;

import com.eaglebank.dto.*;
import com.eaglebank.model.*;
import com.eaglebank.repository.*;
import com.eaglebank.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService
{

    private final BankAccountRepository accountRepo;
    private final ClientRepository clientRepo;

    public BankAccountServiceImpl(BankAccountRepository accountRepo, ClientRepository clientRepo) {
        this.accountRepo = accountRepo;
        this.clientRepo = clientRepo;
    }

    @Override
    public BankAccountResponse createAccount(CreateBankAccountRequest request) {
        Client client = clientRepo.findById(request.clientId).orElseThrow();
        BankAccount acc = new BankAccount();
        acc.setType(request.type);
        acc.setClient(client);
        accountRepo.save(acc);
        return map(acc);
    }

    @Override
    public List<BankAccountResponse> listAccounts() {
        return accountRepo.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public BankAccountResponse getAccount(Long accountNumber) {
        return map(accountRepo.findById(accountNumber).orElseThrow());
    }

    @Override
    public BankAccountResponse updateBankAccount(Long accountNumber, UpdateBankAccountRequest request) {
        BankAccount acc = accountRepo.findById(accountNumber).orElseThrow();
        acc.setType(request.type);
        return map(accountRepo.save(acc));
    }

    @Override
    public void deleteAccount(Long accountNumber) {
        accountRepo.deleteById(accountNumber);
    }

    private BankAccountResponse map(BankAccount acc) {
        BankAccountResponse res = new BankAccountResponse();
        res.accountNumber = acc.getAccountNumber();
        res.type = acc.getType();
        res.clientId = acc.getClient().getId();
        return res;
    }
}
