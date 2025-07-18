package com.eaglebank.pojo;

import java.math.BigDecimal;

public class BankAccountResponse {
    private Long accountNumber;
    private String type;
    private BigDecimal balance;

    public BankAccountResponse() {}

    public BankAccountResponse(Long accountNumber, String type, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
    }

    public Long getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Long accountNumber) { this.accountNumber = accountNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
