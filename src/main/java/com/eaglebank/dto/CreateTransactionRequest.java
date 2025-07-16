package com.eaglebank.dto;

import com.eaglebank.model.TransactionType;
import java.math.BigDecimal;

public class CreateTransactionRequest {
    public TransactionType type;
    public BigDecimal amount;
}
