package com.nur.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AccountHolderAddress {

    private String street;

    private String city;

    private String state;

    private String postalCode;

    private String country;
}
