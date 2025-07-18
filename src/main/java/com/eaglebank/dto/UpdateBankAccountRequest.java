package com.eaglebank.dto;

public class UpdateBankAccountRequest {
    private String type;

    public UpdateBankAccountRequest() {}

    public UpdateBankAccountRequest(String type) {
        this.type = type;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
