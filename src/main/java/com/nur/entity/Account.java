package com.nur.entity;

import com.nur.dto.AccountHolderAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String accountNumber;

    @NotBlank
    @Size(max = 100)
    private String accountHolderName;

    @NotNull
    private BigDecimal balance;

    @Embedded
    private AccountHolderAddress address;
}
