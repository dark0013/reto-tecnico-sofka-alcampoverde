package com.alcampoverde.ms_transactions.infraestructure.in.handler;

import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.domain.port.in.IAccountMovementServicePort;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementDto;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementReportDto;
import com.alcampoverde.ms_transactions.infraestructure.in.mapper.IMovementMapper;
import com.alcampoverde.ms_transactions.infraestructure.in.mapper.IMovementRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovementHandler {

    private final IAccountMovementServicePort movimentService;
    private final IMovementMapper movementMapper;
    private final IMovementRepo movementReportMapper;


    public MovementDto findById(Integer id) {
        Movement movementObj = movimentService.findById(id);
        return movementMapper.toDto(movementObj);
    }

    public List<MovementDto> findAll() {
        return movimentService.findAll().stream().map(movementMapper::toDto).collect(Collectors.toList());
    }

    public MovementDto transaction(MovementDto transaction) {
        Movement movement = movementMapper.toDomain(transaction);
        Movement movementObj = movimentService.transaction(movement);
        return movementMapper.toDto(movementObj);
    }

    public void deactivateMovement(Integer movementId) {
        movimentService.deactivateMovement(movementId);
    }

    public List<MovementReportDto> findByAccountIdAndDate(Integer accountId, LocalDate startDate, LocalDate endDate) {
        List<MovementReport> MovementReport = movimentService.findByAccountIdAndDate(accountId, startDate, endDate);
        return MovementReport.stream().map(movementReportMapper::toDto).collect(Collectors.toList());
    }
}
