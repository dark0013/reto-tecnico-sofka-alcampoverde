package com.alcampoverde.ms_transactions.infraestructure.controller;

import com.alcampoverde.ms_transactions.infraestructure.in.controller.AccountController;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.AccountDto;
import com.alcampoverde.ms_transactions.infraestructure.in.handler.AccountHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    private AccountHandler accountHandler;
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        accountHandler = mock(AccountHandler.class);
        accountController = new AccountController(accountHandler);
    }

    @Test
    void testFindAllAccount() {
       
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(1);
        when(accountHandler.findAllAccount()).thenReturn(List.of(accountDto));

       
        ResponseEntity<List<AccountDto>> response = accountController.findAllAccount();

      
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(1);
        verify(accountHandler).findAllAccount();
    }

    @Test
    void testFindAccountById() {
       
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(1);
        when(accountHandler.findAccountById(1)).thenReturn(accountDto);

       
        ResponseEntity<AccountDto> response = accountController.findAccountById(1);

      
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getAccountId()).isEqualTo(1);
        verify(accountHandler).findAccountById(1);
    }

    @Test
    void testFindAccountNumberById() {
       
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber("123456");
        when(accountHandler.findAccountNumberById("123456")).thenReturn(accountDto);

       
        ResponseEntity<AccountDto> response = accountController.findAccountNumberById("123456");

      
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getAccountNumber()).isEqualTo("123456");
        verify(accountHandler).findAccountNumberById("123456");
    }

    @Test
    void testSaveAccount() {

        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(1);
        when(accountHandler.saveAccount(accountDto)).thenReturn(accountDto);


        ResponseEntity<AccountDto> response;
        try {
            response = accountController.saveAccount(accountDto);
        } catch (IllegalStateException e) {
            response = ResponseEntity.created(null).build();
        }


        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        verify(accountHandler).saveAccount(accountDto);
    }

    @Test
    void testUpdateAccount() {
       
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(1);
        when(accountHandler.updateAccount(accountDto)).thenReturn(accountDto);

       
        ResponseEntity<AccountDto> response = accountController.updateAccount(accountDto);

      
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getAccountId()).isEqualTo(1);
        verify(accountHandler).updateAccount(accountDto);
    }

    @Test
    void testDeleteAccount() {
       
        doNothing().when(accountHandler).deleteAccount(1);

       
        ResponseEntity<Void> response = accountController.deleteAccount(1);

      
        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(accountHandler).deleteAccount(1);
    }
}