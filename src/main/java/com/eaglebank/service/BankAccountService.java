package com.eaglebank.service;

import com.eaglebank.model.BankAccount;
import com.eaglebank.model.Client;
import com.eaglebank.repository.BankAccountRepository;
import com.eaglebank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public BankAccount createBankAccount(Long clientId, BankAccount account) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isPresent()) {
            account.setClient(clientOpt.get());
            account.setBalance(BigDecimal.ZERO);
            return accountRepository.save(account);
        }
        return null;
    }

    public List<BankAccount> getBankAccountsByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }

    public Optional<BankAccount> getBankAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public BankAccount updateBankAccount(Long accountId, BankAccount updatedAccount) {
        return accountRepository.findById(accountId).map(account -> {
            account.setAccountNumber(updatedAccount.getAccountNumber());
            return accountRepository.save(account);
        }).orElse(null);
    }

    public void deleteBankAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
