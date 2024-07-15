package com.nur.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountSearchResponse {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;

    private Long customerId;
    private String customerName;
    private String email;
    private String phoneNo;
}
