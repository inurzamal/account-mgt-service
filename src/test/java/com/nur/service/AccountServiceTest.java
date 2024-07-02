package com.nur.service;

import com.nur.dto.AccountHolderAddress;
import com.nur.entity.Account;
import com.nur.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

//    @Autowired
    @InjectMocks
    private AccountService accountService;

    @Test
    void testCreateAccount(){
        Account account = new Account();
        AccountHolderAddress address = new AccountHolderAddress();
        address.setCity("Dubai");
        account.setAccountHolderName("zxc");
        account.setId(1L);
        account.setAccountNumber("1234567890");
        account.setBalance(BigDecimal.valueOf(344.00));
        account.setAddress(address);


        when(accountRepository.save(account)).thenReturn(account);

        Account actual = accountService.createAccount(account);

        assertNotNull(actual);
        assertEquals("1234567890",account.getAccountNumber());
        verify(accountRepository, times(1)).save(account);

    }

    @Test
    void testGetAccountByAccountNumber_Success() throws AccountNotFoundException {
        String accountNumber = "12345";
        Account account = new Account();
        account.setAccountNumber(accountNumber);

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        Account fetchedAccount = accountService.getAccountByAccountNumber(accountNumber);

        assertNotNull(fetchedAccount);
        assertEquals(accountNumber, fetchedAccount.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void testGetAccountByAccountNumber_NotFound() {
        String accountNumber = "12345";

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        Exception exception = assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountByAccountNumber(accountNumber);
        });

        assertEquals("Account not found", exception.getMessage());
        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
    }

}