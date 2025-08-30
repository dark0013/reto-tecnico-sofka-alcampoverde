package com.alcampoverde.ms_transactions.application;

import com.alcampoverde.ms_transactions.application.exception.AccountNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.CustomerNotFoundException;
import com.alcampoverde.ms_transactions.application.service.AccountManagementService;
import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IRequestMessagePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountManagementServiceTest {

    private IAccountRepositoryPort accountRepoPort;
    private IRequestMessagePort customerMessagePort;
    private AccountManagementService accountService;

    @BeforeEach
    void setUp() {
        accountRepoPort = mock(IAccountRepositoryPort.class);
        customerMessagePort = mock(IRequestMessagePort.class);
        accountService = new AccountManagementService(accountRepoPort, customerMessagePort);
    }

    @Test
    void testFindAllAccount() {
        
        Account account1 = Account.builder().accountId(1).accountNumber("123").build();
        Account account2 = Account.builder().accountId(2).accountNumber("456").build();
        when(accountRepoPort.findAllAccount()).thenReturn(Arrays.asList(account1, account2));

     
        List<Account> result = accountService.findAllAccount();

       
        assertEquals(2, result.size());
        verify(accountRepoPort, times(1)).findAllAccount();
    }

    @Test
    void testFindAccountById_WhenExists() {
        
        Account account = Account.builder().accountId(1).accountNumber("123").build();
        when(accountRepoPort.findAccountById(1)).thenReturn(Optional.of(account));
       
        Account result = accountService.findAccountById(1);

        assertNotNull(result);
        assertEquals("123", result.getAccountNumber());
        verify(accountRepoPort, times(1)).findAccountById(1);
    }

    @Test
    void testFindAccountById_WhenNotExists() {
        
        when(accountRepoPort.findAccountById(1)).thenReturn(Optional.empty());
        
        assertThrows(AccountNotFoundException.class, () -> accountService.findAccountById(1));
    }

    @Test
    void testFindAccountNumberById_WhenExists() {
        
        Account account = Account.builder().accountId(1).accountNumber("123").build();
        when(accountRepoPort.findAccountNumberById("123")).thenReturn(Optional.of(account));
        
        Account result = accountService.findAccountNumberById("123");
        
        assertNotNull(result);
        assertEquals("123", result.getAccountNumber());
        verify(accountRepoPort, times(1)).findAccountNumberById("123");
    }

    @Test
    void testFindAccountNumberById_WhenNotExists() {
        
        when(accountRepoPort.findAccountNumberById("999")).thenReturn(Optional.empty());
        
        assertThrows(AccountNotFoundException.class, () -> accountService.findAccountNumberById("999"));
    }

    @Test
    void testSaveAccount_WhenCustomerExists() {
        
        Account account = Account.builder().accountId(1).customerId(100).accountNumber("123").build();
        when(customerMessagePort.sendMessage("100")).thenReturn("true");
        when(accountRepoPort.saveAccount(account)).thenReturn(account);
        
        Account result = accountService.saveAccount(account);

        assertNotNull(result);
        assertEquals(1, result.getAccountId());
        verify(accountRepoPort, times(1)).saveAccount(account);
    }

    @Test
    void testSaveAccount_WhenCustomerNotExists() {
        
        Account account = Account.builder().accountId(1).customerId(200).build();
        when(customerMessagePort.sendMessage("200")).thenReturn("false");
        
        assertThrows(CustomerNotFoundException.class, () -> accountService.saveAccount(account));
    }

    @Test
    void testUpdateAccount_WhenValid() {
        
        Account existing = Account.builder().accountId(1).accountNumber("123").customerId(100).build();
        Account update = Account.builder().accountId(1).accountNumber("999").customerId(100).build();

        when(customerMessagePort.sendMessage("100")).thenReturn("true");
        when(accountRepoPort.findAccountById(1)).thenReturn(Optional.of(existing));
        when(accountRepoPort.updateAccount(any(Account.class))).thenReturn(update);
        
        Account result = accountService.updateAccount(update);

        assertNotNull(result);
        assertEquals("999", result.getAccountNumber());
        verify(accountRepoPort, times(1)).updateAccount(any(Account.class));
    }

    @Test
    void testUpdateAccount_WhenCustomerNotExists() {
        
        Account update = Account.builder().accountId(1).customerId(200).build();
        when(customerMessagePort.sendMessage("200")).thenReturn("false");

        
        assertThrows(CustomerNotFoundException.class, () -> accountService.updateAccount(update));
    }

    @Test
    void testUpdateAccount_WhenAccountNotExists() {
        
        Account update = Account.builder().accountId(99).customerId(100).build();
        when(customerMessagePort.sendMessage("100")).thenReturn("true");
        when(accountRepoPort.findAccountById(99)).thenReturn(Optional.empty());

        
        assertThrows(AccountNotFoundException.class, () -> accountService.updateAccount(update));
    }

    @Test
    void testDeleteAccount() {
        
        doNothing().when(accountRepoPort).deleteAccount(1);

        
        accountService.deleteAccount(1);

       
        verify(accountRepoPort, times(1)).deleteAccount(1);
    }
}