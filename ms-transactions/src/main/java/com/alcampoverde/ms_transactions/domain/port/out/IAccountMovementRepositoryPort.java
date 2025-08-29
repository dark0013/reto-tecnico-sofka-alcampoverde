package com.alcampoverde.ms_transactions.domain.port.out;

import com.alcampoverde.ms_transactions.domain.model.Movement;

import java.util.List;
import java.util.Optional;

public interface IAccountMovementRepositoryPort {
    Optional<Movement> findById(Integer id);

    List<Movement> findAll();

    Movement transaction(Movement transaction);
    void cancelTransaction(Integer movementId);
}
