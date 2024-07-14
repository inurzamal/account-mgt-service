package com.nur.dto;

import lombok.Data;

@Data
public class SearchRequest {
    private String accountNumber;
    private String phoneNo;
    private Long customerId;
}
