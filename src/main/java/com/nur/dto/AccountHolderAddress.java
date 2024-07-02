package com.nur.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Embeddable
public class AccountHolderAddress {

    private String street;

    @NotBlank
    @Size(max = 50)
    private String city;

    private String state;

    @Size(max = 10)
    private String postalCode;

    private String country;
}
