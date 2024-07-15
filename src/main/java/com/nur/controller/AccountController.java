package com.nur.controller;

import com.nur.dto.AccountRequest;
import com.nur.dto.AccountResponse;
import com.nur.dto.AccountSearchRequest;
import com.nur.dto.AccountSearchResponse;
import com.nur.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account/create")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        AccountResponse createdAccount = accountService.createAccount(accountRequest);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("account/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String accountNumber) {
        try {
            AccountResponse accountResponse = accountService.getAccountByAccountNumber(accountNumber);
            return new ResponseEntity<>(accountResponse, HttpStatus.OK);
        } catch (AccountNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponse>> getAllAccount() {
        List<AccountResponse> allAccounts = accountService.getAllAccounts();
        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }

    @PutMapping("/account/update/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable("id") Long id, @RequestBody AccountRequest accountRequest) {
        try {
            AccountResponse updatedAccount = accountService.updateAccount(id, accountRequest);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (AccountNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/account/search")
    public ResponseEntity<List<AccountSearchResponse>> searchAccounts(@RequestBody AccountSearchRequest searchRequest) {
        List<AccountSearchResponse> accountSearchResponsList = accountService.searchAccounts(searchRequest);
        return new ResponseEntity<>(accountSearchResponsList, HttpStatus.OK);
    }
}
