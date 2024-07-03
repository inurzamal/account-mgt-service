package com.nur.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {

    @NotBlank
    @Size(max = 10)
    private String accountNumber;

    @NotBlank
    private String accountHolderName;

    @Email
    private String email;

    private String phoneNo;

    private String pan;

    @NotBlank
    private BigDecimal balance;

    private AccountHolderAddress address;

}
