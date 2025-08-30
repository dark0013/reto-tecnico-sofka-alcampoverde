package com.alcampoverde.ms_transactions.infraestructure.controller;


import com.alcampoverde.ms_transactions.infraestructure.in.controller.AccountTransactionController;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementDto;
import com.alcampoverde.ms_transactions.infraestructure.in.handler.MovementHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountTransactionController.class)
class AccountTransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovementHandler movementHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private MovementDto sampleMovement;

    @BeforeEach
    void setUp() {
        sampleMovement = new MovementDto();
        sampleMovement.setMovementId(1);
        sampleMovement.setDate(LocalDate.now());
        sampleMovement.setMovementType("DEPOSIT");
        sampleMovement.setTransactionAmount(BigDecimal.valueOf(100.0));
        sampleMovement.setAccountId(1);
    }

    @Test
    void testFindAllTransaction() throws Exception {
        when(movementHandler.findAll()).thenReturn(List.of(sampleMovement));

        mockMvc.perform(get("/v1/movements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movementId").value(sampleMovement.getMovementId()));

        verify(movementHandler).findAll();
    }

    @Test
    void testCreateTransaction() throws Exception {
        when(movementHandler.transaction(any(MovementDto.class))).thenReturn(sampleMovement);

        mockMvc.perform(post("/v1/movements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleMovement)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/v1/movements/1"));

        verify(movementHandler).transaction(any(MovementDto.class));
    }

    @Test
    void testDeactivateMovement() throws Exception {
        doNothing().when(movementHandler).deactivateMovement(1);

        mockMvc.perform(delete("/v1/movements/1"))
                .andExpect(status().isNoContent());

        verify(movementHandler).deactivateMovement(1);
    }
}