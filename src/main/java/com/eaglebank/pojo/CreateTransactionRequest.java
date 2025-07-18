package com.eaglebank.pojo;

import com.eaglebank.model.TransactionType;
import java.math.BigDecimal;

public class CreateTransactionRequest {
    private TransactionType type;
    private BigDecimal amount;

    public CreateTransactionRequest() {}

    public CreateTransactionRequest(TransactionType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
