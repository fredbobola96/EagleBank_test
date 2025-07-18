package com.eaglebank.service.impl;

import com.eaglebank.pojo.BankAccountResponse;
import com.eaglebank.pojo.CreateBankAccountRequest;
import com.eaglebank.pojo.UpdateBankAccountRequest;
import com.eaglebank.model.BankAccount;
import com.eaglebank.model.Client;
import com.eaglebank.repository.BankAccountRepository;
import com.eaglebank.repository.ClientRepository;
import com.eaglebank.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository accountRepo;
    private final ClientRepository clientRepo;

    public BankAccountServiceImpl(BankAccountRepository accountRepo, ClientRepository clientRepo) {
        this.accountRepo = accountRepo;
        this.clientRepo = clientRepo;
    }
    @Override
    public BankAccountResponse createAccount(CreateBankAccountRequest request)
    {
        Client client = clientRepo.findById(request.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        BankAccount account = new BankAccount();
        account.setType(request.getType());
        account.setBalance(BigDecimal.ZERO);
        account.setClient(client);

        accountRepo.save(account);
        return map(account);
    }

    @Override
    public List<BankAccountResponse> listAccounts()
    {
        return accountRepo.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponse getAccount(Long accountId) {
        BankAccount account = accountRepo.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return map(account);
    }

    @Override
    public BankAccountResponse updateBankAccount(Long accountId, UpdateBankAccountRequest request)
    {
        BankAccount account = accountRepo.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setType(request.getType());
        accountRepo.save(account);
        return map(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        if (!accountRepo.existsById(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        accountRepo.deleteById(accountId);
    }

    private BankAccountResponse map(BankAccount account) {
        return new BankAccountResponse(
                account.getAccountNumber(),
                account.getType(),
                account.getBalance()
        );
    }
}
