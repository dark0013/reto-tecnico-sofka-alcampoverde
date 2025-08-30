package com.alcampoverde.ms_transactions.infraestructure.controller;


import com.alcampoverde.ms_transactions.infraestructure.in.controller.AccountController;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.AccountDto;
import com.alcampoverde.ms_transactions.infraestructure.in.handler.AccountHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountHandler accountHandler;

    private AccountDto sampleAccount;

    @BeforeEach
    void setUp() {
        sampleAccount = new AccountDto();
        sampleAccount.setAccountId(1);
        sampleAccount.setAccountNumber("12345678");
        sampleAccount.setAccountType("SAVINGS");
        sampleAccount.setAvailableBalance(1000.0);
        sampleAccount.setStatus(true);
        sampleAccount.setCustomerId(1);
    }

    @Test
    void testFindAllAccount() throws Exception {
        when(accountHandler.findAllAccount()).thenReturn(List.of(sampleAccount));

        mockMvc.perform(get("/v1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountId").value(sampleAccount.getAccountId()));

        verify(accountHandler).findAllAccount();
    }

    @Test
    void testFindAccountById() throws Exception {
        when(accountHandler.findAccountById(1)).thenReturn(sampleAccount);

        mockMvc.perform(get("/v1/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(sampleAccount.getAccountId()));

        verify(accountHandler).findAccountById(1);
    }

    @Test
    void testFindAccountNumberById() throws Exception {
        when(accountHandler.findAccountNumberById("12345678")).thenReturn(sampleAccount);

        mockMvc.perform(get("/v1/accounts/accountNumber/12345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value(sampleAccount.getAccountNumber()));

        verify(accountHandler).findAccountNumberById("12345678");
    }

    @Test
    void testSaveAccount() throws Exception {
        when(accountHandler.saveAccount(any(AccountDto.class))).thenReturn(sampleAccount);

        mockMvc.perform(post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleAccount)))
                .andExpect(status().isCreated());

        verify(accountHandler).saveAccount(any(AccountDto.class));
    }

    @Test
    void testUpdateAccount() throws Exception {
        when(accountHandler.updateAccount(any(AccountDto.class))).thenReturn(sampleAccount);

        mockMvc.perform(put("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleAccount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(sampleAccount.getAccountId()));

        verify(accountHandler).updateAccount(any(AccountDto.class));
    }

    @Test
    void testDeleteAccount() throws Exception {
        doNothing().when(accountHandler).deleteAccount(1);

        mockMvc.perform(delete("/v1/accounts/1"))
                .andExpect(status().isNoContent());

        verify(accountHandler).deleteAccount(1);
    }
}