package com.alcampoverde.ms_transactions.infraestructure.handler;

import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.domain.port.in.IAccountTransactionPort;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementDto;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementReportDto;
import com.alcampoverde.ms_transactions.infraestructure.in.handler.MovementHandler;
import com.alcampoverde.ms_transactions.infraestructure.in.mapper.IMovementMapper;
import com.alcampoverde.ms_transactions.infraestructure.in.mapper.IMovementRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MovementHandlerTest {

    private IAccountTransactionPort movementServicePort;
    private IMovementMapper movementMapper;
    private IMovementRepo movementReportMapper;
    private MovementHandler movementHandler;

    @BeforeEach
    void setUp() {
        movementServicePort = mock(IAccountTransactionPort.class);
        movementMapper = mock(IMovementMapper.class);
        movementReportMapper = mock(IMovementRepo.class);
        movementHandler = new MovementHandler(movementServicePort, movementMapper, movementReportMapper);
    }

    @Test
    void testFindById() {
      
        Movement movement = Movement.builder()
                .movementId(1)
                .movementType("DEPOSIT")
                .transactionAmount(100.0)
                .date(LocalDateTime.now())
                .build();

        MovementDto movementDto = new MovementDto();
        movementDto.setMovementId(1);
        movementDto.setMovementType("DEPOSIT");
        movementDto.setTransactionAmount(BigDecimal.valueOf(100));
        movementDto.setDate(LocalDate.now());

        when(movementServicePort.findById(1)).thenReturn(movement);
        when(movementMapper.toDto(movement)).thenReturn(movementDto);

        
        MovementDto result = movementHandler.findById(1);

      
        assertThat(result).isNotNull();
        assertThat(result.getMovementId()).isEqualTo(1);
        verify(movementServicePort).findById(1);
        verify(movementMapper).toDto(movement);
    }

    @Test
    void testFindAll() {
      
        Movement movement = Movement.builder().movementId(1).build();
        MovementDto movementDto = new MovementDto();
        movementDto.setMovementId(1);

        when(movementServicePort.findAll()).thenReturn(List.of(movement));
        when(movementMapper.toDto(movement)).thenReturn(movementDto);

        
        List<MovementDto> result = movementHandler.findAll();

      
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMovementId()).isEqualTo(1);
        verify(movementServicePort).findAll();
        verify(movementMapper).toDto(movement);
    }

    @Test
    void testTransaction() {
      
        MovementDto inputDto = new MovementDto();
        inputDto.setMovementId(1);
        inputDto.setMovementType("DEPOSIT");
        inputDto.setTransactionAmount(BigDecimal.valueOf(100));
        inputDto.setDate(LocalDate.now());

        Movement movement = Movement.builder().movementId(1).build();
        Movement movementResult = Movement.builder().movementId(1).build();
        MovementDto resultDto = new MovementDto();
        resultDto.setMovementId(1);

        when(movementMapper.toDomain(inputDto)).thenReturn(movement);
        when(movementServicePort.transaction(movement)).thenReturn(movementResult);
        when(movementMapper.toDto(movementResult)).thenReturn(resultDto);

        
        MovementDto result = movementHandler.transaction(inputDto);

      
        assertThat(result).isNotNull();
        assertThat(result.getMovementId()).isEqualTo(1);
        verify(movementMapper).toDomain(inputDto);
        verify(movementServicePort).transaction(movement);
        verify(movementMapper).toDto(movementResult);
    }

    @Test
    void testDeactivateMovement() {
        
        movementHandler.deactivateMovement(1);

      
        verify(movementServicePort).cancelTransaction(1);
    }

    @Test
    void testGenerateMovementReport() {
      
        MovementReport report = MovementReport.builder()
                .date(LocalDate.now())
                .customerName("John Doe")
                .accountNumber("123")
                .accountType("SAVINGS")
                .availableBalance(1000.0)
                .status(true)
                .transactionAmount(BigDecimal.valueOf(100))
                .movementAvailableBalance(BigDecimal.valueOf(1100))
                .build();

        MovementReportDto reportDto = new MovementReportDto();
        reportDto.setAccountNumber("123");

        when(movementServicePort.generateMovementReport(1, LocalDate.now(), LocalDate.now()))
                .thenReturn(List.of(report));
        when(movementReportMapper.toDto(report)).thenReturn(reportDto);

        
        List<MovementReportDto> result = movementHandler.generateMovementReport(1, LocalDate.now(), LocalDate.now());

      
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAccountNumber()).isEqualTo("123");
        verify(movementServicePort).generateMovementReport(1, LocalDate.now(), LocalDate.now());
        verify(movementReportMapper).toDto(report);
    }
}