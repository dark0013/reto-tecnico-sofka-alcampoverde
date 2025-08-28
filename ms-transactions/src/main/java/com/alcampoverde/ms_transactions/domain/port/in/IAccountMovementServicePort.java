package com.alcampoverde.ms_transactions.domain.port.in;

import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.domain.model.MovementReport;

import java.time.LocalDate;
import java.util.List;

public interface IAccountMovementServicePort {

    Movement findById(Integer id);

    List<Movement> findAll();

    Movement transaction(Movement transaction);

    void deactivateMovement(Integer movementId);

    List<MovementReport> findByAccountIdAndDate(Integer accountId, LocalDate startDate, LocalDate endDate);
}