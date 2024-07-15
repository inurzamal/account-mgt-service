package com.nur.dto;

import lombok.Data;

@Data
public class AccountSearchRequest {
    private String accountNumber;
    private String phoneNo;
    private Long customerId;
}
