package com.nur.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SearchRequest {

    private String accountNumber;

    private BigDecimal balance;

    private Long customerId;
}
