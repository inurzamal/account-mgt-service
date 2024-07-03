package com.nur.service;

import com.nur.dto.AccountRequest;
import com.nur.dto.AccountResponse;
import com.nur.entity.Account;
import com.nur.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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

        // Update the fields of the existing account
        existingAccount.setAccountNumber(accountRequest.getAccountNumber());
        existingAccount.setAccountHolderName(accountRequest.getAccountHolderName());
        existingAccount.setBalance(accountRequest.getBalance());
        existingAccount.setEmail(accountRequest.getEmail());
        existingAccount.setPhoneNo(accountRequest.getPhoneNo());
        existingAccount.setPan(accountRequest.getPan());
        existingAccount.setAddress(accountRequest.getAddress());

        Account updatedAccount = accountRepository.save(existingAccount);
        return mapToResponse(updatedAccount);
    }

    public AccountResponse getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return mapToResponse(account);
    }

    public List<AccountResponse> getAllAccounts(){
        return accountRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private Account mapToEntity(AccountRequest accountRequest) {
        Account account = new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setAccountHolderName(accountRequest.getAccountHolderName());
        account.setBalance(accountRequest.getBalance());
        account.setEmail(accountRequest.getEmail());
        account.setPhoneNo(accountRequest.getPhoneNo());
        account.setPan(accountRequest.getPan());
        account.setAddress(accountRequest.getAddress());
        return account;
    }

    private AccountResponse mapToResponse(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setAccountNumber(account.getAccountNumber());
        accountResponse.setAccountHolderName(account.getAccountHolderName());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setEmail(account.getEmail());
        accountResponse.setPhoneNo(account.getPhoneNo());
        accountResponse.setPan(account.getPan());
        accountResponse.setAddress(account.getAddress());
        return accountResponse;
    }

}
