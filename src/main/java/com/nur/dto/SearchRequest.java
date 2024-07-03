package com.nur.dto;

import lombok.Data;

@Data
public class SearchRequest {

    private String accountNumber;

    private String accountHolderName;

    private String email;

    private String phoneNo;

    private String pan;
}
