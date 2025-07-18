package com.eaglebank.service.impl;

import com.eaglebank.dto.CreateTransactionRequest;
import com.eaglebank.dto.TransactionResponse;
import com.eaglebank.model.BankAccount;
import com.eaglebank.model.Transaction;
import com.eaglebank.model.TransactionType;
import com.eaglebank.repository.BankAccountRepository;
import com.eaglebank.repository.TransactionRepository;
import com.eaglebank.service.TransactionService;
import org.springframework.stereotype.Service;

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

        if (request.getType() == TransactionType.WITHDRAW) {
            if (account.getBalance().compareTo(request.getAmount()) < 0) {
                throw new IllegalArgumentException("Insufficient funds");
            }
            account.setBalance(account.getBalance().subtract(request.getAmount()));
        } else if (request.getType() == TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance().add(request.getAmount()));
        } else {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        // Save updated balance
        accountRepo.save(account);

        // Create and persist transaction
        Transaction tx = new Transaction();
        tx.setType(request.getType());
        tx.setAmount(request.getAmount());
        tx.setTimestamp(LocalDateTime.now());
        tx.setBankAccount(account); // ✅ This must be a valid, persisted account

        txRepo.save(tx);
        return map(tx);
    }

    @Override
    public List<TransactionResponse> getTransactions(Long accountNumber) {
        return txRepo.findByAccount_AccountNumber(accountNumber).stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionResponse getTransaction(Long accountNumber, Long transactionId) {
        Transaction tx = txRepo.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        return map(tx);
    }

    private TransactionResponse map(Transaction tx) {
        return new TransactionResponse(
                tx.getId().toString(),
                tx.getType(),
                tx.getAmount(),
                tx.getTimestamp()
        );
    }
}
