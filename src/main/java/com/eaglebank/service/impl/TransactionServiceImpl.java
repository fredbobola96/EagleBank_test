package com.eaglebank.service.impl;

import com.eaglebank.dto.*;
import com.eaglebank.model.*;
import com.eaglebank.repository.*;
import com.eaglebank.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository txRepo;
    private final BankAccountRepository accountRepo;

    public TransactionServiceImpl(TransactionRepository txRepo, BankAccountRepository accountRepo) {
        this.txRepo = txRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public TransactionResponse createTransaction(Long accountNumber, CreateTransactionRequest request) {
        BankAccount account = accountRepo.findById(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));

        if (request.type == TransactionType.WITHDRAW) {
            if (account.getBalance().compareTo(request.amount) < 0) {
                throw new IllegalArgumentException("Insufficient funds");
            }
            account.setBalance(account.getBalance().subtract(request.amount));
        } else if (request.type == TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance().add(request.amount));
        } else {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        accountRepo.save(account);

        Transaction tx = new Transaction();
        tx.setType(request.type);
        tx.setAmount(request.amount);
        tx.setTimestamp(LocalDateTime.now());
        tx.setBankAccount(account);

        txRepo.save(tx);
        return map(tx);
    }

    @Override
    public List<TransactionResponse> getTransactions(Long accountNumber)
    {
        return txRepo.findByAccount_AccountNumber(accountNumber).stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponse getTransaction(Long accountNumber, Long transactionId)
    {
        return map(txRepo.findById(transactionId).orElseThrow());
    }

    private TransactionResponse map(Transaction tx) {
        TransactionResponse res = new TransactionResponse();
        res.id = tx.getId();
        res.type = tx.getType();
        res.amount = tx.getAmount();
        res.timestamp = tx.getTimestamp();
        return res;
    }
}
