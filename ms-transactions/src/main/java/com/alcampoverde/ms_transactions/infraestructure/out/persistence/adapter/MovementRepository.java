package com.alcampoverde.ms_transactions.infraestructure.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alcampoverde.ms_transactions.application.exception.AccountMovementNotFoundException;
import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountMovementRepositoryPort;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.entity.MovementEntity;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.mapper.IAccountMovementRepositoryMapper;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.repository.IMovementRepository;
import org.springframework.stereotype.Repository;


@Repository
public class MovementRepository implements IAccountMovementRepositoryPort {

    private final IMovementRepository movementRepository;
    private final IAccountMovementRepositoryMapper accountMovementRepositoryMapper;

    public MovementRepository(IMovementRepository movementRepository, IAccountMovementRepositoryMapper accountMovementRepositoryMapper) {
        this.movementRepository = movementRepository;
        this.accountMovementRepositoryMapper = accountMovementRepositoryMapper;
    }

    @Override
    public Optional<Movement> findById(Integer id) {
        Optional<MovementEntity> movementEntity = movementRepository.findById(id);
        return movementEntity.map(accountMovementRepositoryMapper::toDomain);

    }

    @Override
    public List<Movement> findAll() {
        return movementRepository.findAll().stream()
                .map(accountMovementRepositoryMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Movement transaction(Movement transaction) {
        MovementEntity movementEntity = accountMovementRepositoryMapper.toEntity(transaction);
        MovementEntity movement = movementRepository.save(movementEntity);
        return accountMovementRepositoryMapper.toDomain(movement);
    }

    @Override
    public void deactivateMovement(Integer movementId) {
        Optional<MovementEntity> entityOpt = movementRepository.findById(movementId);
        if (entityOpt.isPresent()) {
            MovementEntity entity = entityOpt.get();
            entity.setStatus(false);
            movementRepository.save(entity);
        } else {
            throw new AccountMovementNotFoundException("Movement not found with ID: " + movementId);
        }
    }


}
