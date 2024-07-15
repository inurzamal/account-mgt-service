package com.nur.service;

import com.nur.dto.AccountRequest;
import com.nur.dto.AccountResponse;
import com.nur.dto.AccountSearchRequest;
import com.nur.dto.AccountSearchResponse;
import com.nur.entity.Account;
import com.nur.entity.Customer;
import com.nur.repository.AccountRepository;
import com.nur.repository.CustomerRepository;
import com.nur.specification.AccountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public AccountResponse createAccount(AccountRequest accountRequest) {
        Customer customer = customerRepository.findById(accountRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Account account = new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setBalance(accountRequest.getBalance());
        account.setCustomer(customer);
        Account savedAccount = accountRepository.save(account);
        return mapToResponse(savedAccount);
    }

    public AccountResponse updateAccount(Long id, AccountRequest accountRequest) throws AccountNotFoundException {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        Customer customer = customerRepository.findById(accountRequest.getCustomerId())
                .orElseThrow(() -> new AccountNotFoundException("Customer not found"));

        existingAccount.setAccountNumber(accountRequest.getAccountNumber());
        existingAccount.setBalance(accountRequest.getBalance());
        existingAccount.setCustomer(customer);

        Account updatedAccount = accountRepository.save(existingAccount);
        return mapToResponse(updatedAccount);
    }

    public AccountResponse getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return mapToResponse(account);
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<AccountSearchResponse> searchAccounts(AccountSearchRequest searchRequest) {
        return accountRepository.findAll(
                AccountSpecification.hasAccountNumber(searchRequest.getAccountNumber())
                        .and(AccountSpecification.hasCustomerId(searchRequest.getCustomerId()))
                        .and(AccountSpecification.hasPhoneNo(searchRequest.getPhoneNo()))
        ).stream().map(this::mapToSearchResponse).toList();
    }

    private Account mapToEntity(AccountRequest accountRequest) {
        Account account = new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setBalance(accountRequest.getBalance());
        account.setCustomer(customerRepository.findById(accountRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found")));
        return account;
    }

    private AccountResponse mapToResponse(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setCustomerId(account.getCustomer().getId());
        return accountResponse;
    }

    private AccountSearchResponse mapToSearchResponse(Account account) {
        AccountSearchResponse accountSearchResponse = new AccountSearchResponse();
        accountSearchResponse.setId(account.getId());
        accountSearchResponse.setAccountNumber(account.getAccountNumber());
        accountSearchResponse.setBalance(account.getBalance());
        accountSearchResponse.setCustomerId(account.getCustomer().getId());
        accountSearchResponse.setCustomerName(account.getCustomer().getFirstName()+" "+account.getCustomer().getLastName());
        accountSearchResponse.setEmail(account.getCustomer().getEmail());
        accountSearchResponse.setPhoneNo(account.getCustomer().getPhoneNo());
        return accountSearchResponse;
    }
}
