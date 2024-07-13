//package com.nur.service;
//
//import com.nur.dto.CustomerAddress;
//import com.nur.dto.AccountRequest;
//import com.nur.dto.AccountResponse;
//import com.nur.entity.Account;
//import com.nur.repository.AccountRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.security.auth.login.AccountNotFoundException;
//import java.math.BigDecimal;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class AccountServiceTest {
//
//    @Mock
//    private AccountRepository accountRepository;
//
////    @Autowired
//    @InjectMocks
//    private AccountService accountService;
//
//    @Test
//    void testCreateAccount(){
//        Account account = new Account();
//        CustomerAddress address = new CustomerAddress();
//        address.setCity("Dubai");
//        account.setAccountHolderName("zxc");
//        account.setAccountNumber("1234567890");
//        account.setBalance(BigDecimal.valueOf(344.00));
//        account.setAddress(address);
//
//        when(accountRepository.save(account)).thenReturn(account);
//
//        AccountRequest accountRequest = new AccountRequest();
//        accountRequest.setAccountHolderName("zxc");
//        accountRequest.setAccountNumber("1234567890");
//        accountRequest.setBalance(BigDecimal.valueOf(344.00));
//        accountRequest.setAddress(address);
//
//        AccountResponse accountResponse = accountService.createAccount(accountRequest);
//
//        assertNotNull(accountResponse);
//        assertEquals("1234567890",account.getAccountNumber());
//        verify(accountRepository, times(1)).save(account);
//
//    }
//
//    @Test
//    void testGetAccountByAccountNumber_Success() throws AccountNotFoundException {
//        String accountNumber = "12345";
//        Account account = new Account();
//        account.setAccountNumber(accountNumber);
//
//        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
//
//        AccountResponse accountResponse = accountService.getAccountByAccountNumber(accountNumber);
//
//        assertNotNull(accountResponse);
//        assertEquals(accountNumber, accountResponse.getAccountNumber());
//        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
//    }
//
//    @Test
//    void testGetAccountByAccountNumber_NotFound() {
//        String accountNumber = "12345";
//
//        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(AccountNotFoundException.class, () -> {
//            accountService.getAccountByAccountNumber(accountNumber);
//        });
//
//        assertEquals("Account not found", exception.getMessage());
//        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
//    }
//
//}
