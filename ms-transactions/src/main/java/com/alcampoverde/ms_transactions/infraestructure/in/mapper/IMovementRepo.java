package com.alcampoverde.ms_transactions.infraestructure.in.mapper;

import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementReportDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMovementRepo {
    MovementReportDto toDto(MovementReport movement);


    MovementReport toDomain(MovementReportDto movement);
}
