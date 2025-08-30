package com.alcampoverde.ms_transactions.infraestructure.out.persistence.adapter;

import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.domain.port.out.IMovementReportRepositoryPort;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.dto.TransactionReportDto;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.mapper.IMovementReportRepositoryMapper;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.repository.IMovementReportRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovementReportRepositoryAdapter implements IMovementReportRepositoryPort {

    private final IMovementReportRepository movementReportRepository;
    private final IMovementReportRepositoryMapper movementReportRepositoryMapper;

    public MovementReportRepositoryAdapter(IMovementReportRepository movementReportRepository, IMovementReportRepositoryMapper movementReportRepositoryMapper) {
        this.movementReportRepository = movementReportRepository;
        this.movementReportRepositoryMapper = movementReportRepositoryMapper;
    }

    @Override
    public List<MovementReport> findByAccountIdAndDate(Integer accountId,LocalDate startDate, LocalDate endDate) {
        List<TransactionReportDto> movementEntity = movementReportRepository.findByAccountIdAndDate(accountId,startDate,endDate);
        return movementEntity.stream()
                .map(movementReportRepositoryMapper::toDomain)
                .collect(Collectors.toList());
    }

}