package com.eaglebank.dto;

public class CreateBankAccountRequest {
    private Long clientId;
    private String type;

    public CreateBankAccountRequest() {}

    public CreateBankAccountRequest(Long clientId, String type) {
        this.clientId = clientId;
        this.type = type;
    }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
