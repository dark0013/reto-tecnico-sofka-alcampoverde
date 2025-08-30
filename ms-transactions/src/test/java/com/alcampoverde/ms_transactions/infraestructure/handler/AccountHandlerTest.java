package com.alcampoverde.ms_transactions.infraestructure.handler;


import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.port.in.IAccountManagementPort;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.AccountDto;
import com.alcampoverde.ms_transactions.infraestructure.in.handler.AccountHandler;
import com.alcampoverde.ms_transactions.infraestructure.in.mapper.IAccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AccountHandlerTest {

    @Mock
    private IAccountManagementPort accountService;

    @Mock
    private IAccountMapper accountMapper;

    @InjectMocks
    private AccountHandler accountHandler;

    private Account account;
    private AccountDto accountDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        account = Account.builder()
                .accountId(1)
                .accountNumber("123456")
                .accountType("SAVINGS")
                .availableBalance(1000.0)
                .status(true)
                .customerId(1)
                .build();

        accountDto = new AccountDto();
        accountDto.setAccountId(1);
        accountDto.setAccountNumber("123456");
        accountDto.setAccountType("SAVINGS");
        accountDto.setAvailableBalance(1000.0);
        accountDto.setStatus(true);
        accountDto.setCustomerId(1);
    }

    @Test
    void testFindAllAccount() {
        
        when(accountService.findAllAccount()).thenReturn(List.of(account));
        when(accountMapper.toDto(account)).thenReturn(accountDto);

      
        List<AccountDto> result = accountHandler.findAllAccount();

       
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAccountId()).isEqualTo(account.getAccountId());
        verify(accountService).findAllAccount();
        verify(accountMapper).toDto(account);
    }

    @Test
    void testFindAccountById() {
        
        when(accountService.findAccountById(1)).thenReturn(account);
        when(accountMapper.toDto(account)).thenReturn(accountDto);

      
        AccountDto result = accountHandler.findAccountById(1);

       
        assertThat(result.getAccountId()).isEqualTo(account.getAccountId());
        verify(accountService).findAccountById(1);
        verify(accountMapper).toDto(account);
    }

    @Test
    void testFindAccountNumberById() {
        
        when(accountService.findAccountNumberById("123456")).thenReturn(account);
        when(accountMapper.toDto(account)).thenReturn(accountDto);

      
        AccountDto result = accountHandler.findAccountNumberById("123456");

       
        assertThat(result.getAccountNumber()).isEqualTo(account.getAccountNumber());
        verify(accountService).findAccountNumberById("123456");
        verify(accountMapper).toDto(account);
    }

    @Test
    void testSaveAccount() {
        
        when(accountMapper.toDomain(accountDto)).thenReturn(account);
        when(accountService.saveAccount(account)).thenReturn(account);
        when(accountMapper.toDto(account)).thenReturn(accountDto);

      
        AccountDto result = accountHandler.saveAccount(accountDto);

       
        assertThat(result.getAccountId()).isEqualTo(account.getAccountId());
        verify(accountMapper).toDomain(accountDto);
        verify(accountService).saveAccount(account);
        verify(accountMapper).toDto(account);
    }

    @Test
    void testUpdateAccount() {
        
        when(accountMapper.toDomain(accountDto)).thenReturn(account);
        when(accountService.updateAccount(account)).thenReturn(account);
        when(accountMapper.toDto(account)).thenReturn(accountDto);

      
        AccountDto result = accountHandler.updateAccount(accountDto);

       
        assertThat(result.getAccountId()).isEqualTo(account.getAccountId());
        verify(accountMapper).toDomain(accountDto);
        verify(accountService).updateAccount(account);
        verify(accountMapper).toDto(account);
    }

    @Test
    void testDeleteAccount() {
        
        doNothing().when(accountService).deleteAccount(1);
      
        accountHandler.deleteAccount(1);

       
        verify(accountService).deleteAccount(1);
    }
}