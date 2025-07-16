package com.eaglebank.dto;

import com.eaglebank.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
    public Long id;
    public TransactionType type;
    public BigDecimal amount;
    public LocalDateTime timestamp;
}