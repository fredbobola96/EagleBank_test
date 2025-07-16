package com.eaglebank.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accountNumber;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public BankAccount() {}

    public BankAccount(int id, String accountNumber, BigDecimal balance, Client client) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.client = client;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
