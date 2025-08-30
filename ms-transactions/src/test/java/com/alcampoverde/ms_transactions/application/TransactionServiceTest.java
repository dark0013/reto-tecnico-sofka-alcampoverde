package com.alcampoverde.ms_transactions.application;

import com.alcampoverde.ms_transactions.application.exception.AccountMovementNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.AccountNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.InsufficientBalanceException;
import com.alcampoverde.ms_transactions.application.exception.InvalidMovementTypeException;
import com.alcampoverde.ms_transactions.application.service.TransactionService;
import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountMovementRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IMovementReportRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private IAccountMovementRepositoryPort movementRepositoryPort;

    @Mock
    private IAccountRepositoryPort accountRepositoryPort;

    @Mock
    private IMovementReportRepositoryPort movementReportRepositoryPort;

    @InjectMocks
    private TransactionService transactionService;

    private Account account;
    private Movement depositMovement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        account = Account.builder().accountId(1).availableBalance(1000.0).build();

        depositMovement = Movement.builder().movementId(1).movementType("DEPOSIT").transactionAmount(200.0).account(account).build();
    }

    @Test
    void testFindByIdSuccess() {
        // Arrange
        when(movementRepositoryPort.findById(1)).thenReturn(Optional.of(depositMovement));

        // Act
        Movement result = transactionService.findById(1);

        // Assert
        assertThat(result).isEqualTo(depositMovement);
        verify(movementRepositoryPort).findById(1);
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(movementRepositoryPort.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AccountMovementNotFoundException.class, () -> transactionService.findById(1));
        verify(movementRepositoryPort).findById(1);
    }

    @Test
    void testFindAll() {
        // Arrange
        Movement m2 = Movement.builder().movementId(2).movementType("WITHDRAWAL").transactionAmount(50.0).account(account).build();
        when(movementRepositoryPort.findAll()).thenReturn(List.of(depositMovement, m2));

        // Act
        List<Movement> result = transactionService.findAll();

        // Assert
        assertThat(result).hasSize(2);
        verify(movementRepositoryPort).findAll();
    }

    @Test
    void testTransactionDeposit() {
        // Arrange
        when(accountRepositoryPort.findAccountById(account.getAccountId())).thenReturn(Optional.of(account));
        when(movementRepositoryPort.transaction(any())).thenReturn(depositMovement);

        // Act
        Movement result = transactionService.transaction(depositMovement);

        // Assert
        assertThat(result).isEqualTo(depositMovement);
        assertThat(account.getAvailableBalance()).isEqualTo(1200.0);
        verify(accountRepositoryPort).findAccountById(account.getAccountId());
        verify(movementRepositoryPort).transaction(depositMovement);
    }

    @Test
    void testTransactionWithdrawalInsufficientBalance() {
        // Arrange
        Movement withdrawal = Movement.builder().movementId(2).movementType("WITHDRAWAL").transactionAmount(2000.0) // mayor al balance
                .account(account).build();
        when(accountRepositoryPort.findAccountById(account.getAccountId())).thenReturn(Optional.of(account));

        // Act & Assert
        assertThrows(InsufficientBalanceException.class, () -> transactionService.transaction(withdrawal));
        verify(accountRepositoryPort).findAccountById(account.getAccountId());
    }

    @Test
    void testCancelTransaction() {
        // Arrange
        doNothing().when(movementRepositoryPort).cancelTransaction(1);

        // Act
        transactionService.cancelTransaction(1);

        // Assert
        verify(movementRepositoryPort).cancelTransaction(1);
    }

    @Test
    void testGenerateMovementReport() {
        // Arrange
        MovementReport report = MovementReport.builder()
                .date(LocalDate.now())
                .customerName("Juan Perez")
                .accountNumber("123456789")
                .accountType("SAVINGS")
                .availableBalance(1000.0)
                .status(true)
                .transactionAmount(BigDecimal.valueOf(200))
                .movementAvailableBalance(BigDecimal.valueOf(1200))
                .build();

        when(movementReportRepositoryPort.findByAccountIdAndDate(1, LocalDate.now(), LocalDate.now()))
                .thenReturn(List.of(report));

        // Act
        List<MovementReport> result = transactionService.generateMovementReport(1, LocalDate.now(), LocalDate.now());

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCustomerName()).isEqualTo("Juan Perez");
        verify(movementReportRepositoryPort).findByAccountIdAndDate(1, LocalDate.now(), LocalDate.now());
    }
}