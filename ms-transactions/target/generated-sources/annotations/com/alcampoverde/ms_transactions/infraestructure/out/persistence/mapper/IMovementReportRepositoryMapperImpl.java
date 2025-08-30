package com.alcampoverde.ms_transactions.infraestructure.out.persistence.mapper;

import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.dto.TransactionReportDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T05:26:35-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class IMovementReportRepositoryMapperImpl implements IMovementReportRepositoryMapper {

    @Override
    public MovementReport toDomain(TransactionReportDto entity) {
        if ( entity == null ) {
            return null;
        }

        MovementReport.MovementReportBuilder movementReport = MovementReport.builder();

        movementReport.date( entity.getDate() );
        movementReport.customerName( entity.getCustomerName() );
        movementReport.accountNumber( entity.getAccountNumber() );
        movementReport.accountType( entity.getAccountType() );
        movementReport.availableBalance( entity.getAvailableBalance() );
        movementReport.status( entity.getStatus() );
        movementReport.transactionAmount( entity.getTransactionAmount() );
        movementReport.movementAvailableBalance( entity.getMovementAvailableBalance() );

        return movementReport.build();
    }
}
