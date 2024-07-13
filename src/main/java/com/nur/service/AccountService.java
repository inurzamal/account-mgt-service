package com.nur.service;

import com.nur.dto.AccountRequest;
import com.nur.dto.AccountResponse;
import com.nur.dto.SearchRequest;
import com.nur.entity.Account;
import com.nur.repository.AccountRepository;
import com.nur.specification.AccountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountResponse createAccount(AccountRequest accountRequest) {
        Account account = mapToEntity(accountRequest);
        Account savedAccount = accountRepository.save(account);
        return mapToResponse(savedAccount);
    }

    public AccountResponse updateAccount(Long id, AccountRequest accountRequest) throws AccountNotFoundException {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        existingAccount.setAccountNumber(accountRequest.getAccountNumber());
        existingAccount.setBalance(accountRequest.getBalance());
        existingAccount.setCustomerId(accountRequest.getCustomerId());

        Account updatedAccount = accountRepository.save(existingAccount);
        return mapToResponse(updatedAccount);
    }

    public AccountResponse getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return mapToResponse(account);
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<AccountResponse> searchAccounts(SearchRequest searchRequest) {
        // Update search logic to include customerId if needed
        return accountRepository.findAll(
                AccountSpecification.hasAccountNumber(searchRequest.getAccountNumber())
                        .and(AccountSpecification.hasCustomerId(searchRequest.getCustomerId()))
        ).stream().map(this::mapToResponse).toList();
    }

    private Account mapToEntity(AccountRequest accountRequest) {
        Account account = new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setBalance(accountRequest.getBalance());
        account.setCustomerId(accountRequest.getCustomerId());
        return account;
    }

    private AccountResponse mapToResponse(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setCustomerId(account.getCustomerId());
        return accountResponse;
    }
}
