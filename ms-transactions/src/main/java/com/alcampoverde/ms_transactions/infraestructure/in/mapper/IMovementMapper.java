package com.alcampoverde.ms_transactions.infraestructure.in.mapper;

import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMovementMapper {

    @InheritInverseConfiguration
    MovementDto toDto(Movement transaction);

    @Mapping(target = "account.accountId", source = "accountId")
    Movement toDomain(MovementDto transaction);
}