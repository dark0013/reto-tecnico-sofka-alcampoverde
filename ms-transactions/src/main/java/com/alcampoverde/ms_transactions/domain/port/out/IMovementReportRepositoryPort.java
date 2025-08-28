package com.alcampoverde.ms_transactions.domain.port.out;

import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import java.time.LocalDate;
import java.util.List;

public interface IMovementReportRepositoryPort {

    List<MovementReport> findByAccountIdAndDate(Integer accountId,LocalDate startDate, LocalDate endDate);
}