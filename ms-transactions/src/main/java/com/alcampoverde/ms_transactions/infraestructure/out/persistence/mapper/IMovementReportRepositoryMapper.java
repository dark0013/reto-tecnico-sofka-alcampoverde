package com.alcampoverde.ms_transactions.infraestructure.out.persistence.mapper;

import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.dto.TransactionReportDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMovementReportRepositoryMapper {
    MovementReport toDomain(TransactionReportDto entity);

}
