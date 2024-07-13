package com.nur.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {

    @NotBlank
    @Size(max = 10)
    private String accountNumber;

    private BigDecimal balance;

    private Long customerId; // Reference to Customer ID
}

