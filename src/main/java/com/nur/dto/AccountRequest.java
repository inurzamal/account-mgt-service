package com.nur.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class AccountRequest {

    @NotBlank
    @Size(max = 10)
    private String accountNumber;

    private BigDecimal balance;

    @NotBlank
    private Long customerId; // Reference to Customer ID
}
