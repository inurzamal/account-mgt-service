package com.nur.entity;

import com.nur.dto.AccountHolderAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @Size(max = 10)
    private String accountNumber;

    private String accountHolderName;

    @Email
    private String email;

    private String phoneNo;

    private String pan;

    private BigDecimal balance;

    @Embedded
    private AccountHolderAddress address;

}