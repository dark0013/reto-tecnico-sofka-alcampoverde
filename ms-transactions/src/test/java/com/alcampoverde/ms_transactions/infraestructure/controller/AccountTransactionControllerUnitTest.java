package com.alcampoverde.ms_transactions.infraestructure.controller;


import com.alcampoverde.ms_transactions.infraestructure.in.controller.AccountTransactionController;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementDto;
import com.alcampoverde.ms_transactions.infraestructure.in.handler.MovementHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AccountTransactionControllerUnitTest {

    private MovementHandler movementHandler;
    private AccountTransactionController controller;
    private MovementDto sampleMovement;

    @BeforeEach
    void setUp() {
        movementHandler = mock(MovementHandler.class);
        controller = new AccountTransactionController(movementHandler);

        sampleMovement = new MovementDto();
        sampleMovement.setMovementId(1);
        sampleMovement.setMovementType("DEPOSIT");
        sampleMovement.setTransactionAmount(new BigDecimal("100.0"));
        sampleMovement.setAccountId(1);
        sampleMovement.setDate(LocalDate.now());
    }

    @Test
    void testFindAllTransaction() {
        when(movementHandler.findAll()).thenReturn(List.of(sampleMovement));

        ResponseEntity<List<MovementDto>> response = controller.findAllTransaction();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(1).contains(sampleMovement);
        verify(movementHandler).findAll();
    }

    @Test
    void testCreateTransaction() {
        when(movementHandler.transaction(sampleMovement)).thenReturn(sampleMovement);

        MovementDto result = movementHandler.transaction(sampleMovement);

        assertThat(result).isEqualTo(sampleMovement);
        verify(movementHandler).transaction(sampleMovement);
    }


    @Test
    void testDeactivateMovement() {
        doNothing().when(movementHandler).deactivateMovement(1);

        ResponseEntity<Void> response = controller.deactivateMovement(1);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(movementHandler).deactivateMovement(1);
    }
}