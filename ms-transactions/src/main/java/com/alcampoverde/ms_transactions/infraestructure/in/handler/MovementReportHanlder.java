package com.alcampoverde.ms_transactions.infraestructure.in.handler;

import com.alcampoverde.ms_transactions.application.service.TransactionService;
import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementReportDto;
import com.alcampoverde.ms_transactions.infraestructure.in.mapper.IMovementRepo;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class MovementReportHanlder {

    private final TransactionService movementService;
    private final IMovementRepo movementRepo;

    public List<MovementReportDto> generateMovementReport(Integer accountId,LocalDate startDate, LocalDate endDate) {
        List<MovementReport> obj = movementService.generateMovementReport(accountId,startDate, endDate);

        return obj.stream()
                .map(movementRepo::toDto)
                .collect(Collectors.toList());
    }

}
