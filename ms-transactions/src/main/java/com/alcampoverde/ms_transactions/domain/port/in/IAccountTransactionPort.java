package com.alcampoverde.ms_transactions.domain.port.in;

import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.domain.model.MovementReport;

import java.time.LocalDate;
import java.util.List;

public interface IAccountTransactionPort {

    Movement findById(Integer id);

    List<Movement> findAll();

    Movement transaction(Movement transaction);

    void cancelTransaction(Integer movementId);

    List<MovementReport> generateMovementReport(Integer accountId, LocalDate startDate, LocalDate endDate);
}