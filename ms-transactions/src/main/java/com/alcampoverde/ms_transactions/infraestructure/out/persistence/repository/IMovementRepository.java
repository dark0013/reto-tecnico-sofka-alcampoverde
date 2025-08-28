package com.alcampoverde.ms_transactions.infraestructure.out.persistence.repository;

import com.alcampoverde.ms_transactions.infraestructure.out.persistence.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovementRepository extends JpaRepository<MovementEntity, Integer> {

}
