package com.alcampoverde.ms_transactions.infraestructure.in.mapper;

import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementReportDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T05:26:35-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class IMovementRepoImpl implements IMovementRepo {

    @Override
    public MovementReportDto toDto(MovementReport movement) {
        if ( movement == null ) {
            return null;
        }

        MovementReportDto movementReportDto = new MovementReportDto();

        movementReportDto.setDate( movement.getDate() );
        movementReportDto.setCustomerName( movement.getCustomerName() );
        movementReportDto.setAccountNumber( movement.getAccountNumber() );
        movementReportDto.setAccountType( movement.getAccountType() );
        movementReportDto.setAvailableBalance( movement.getAvailableBalance() );
        movementReportDto.setStatus( movement.getStatus() );
        movementReportDto.setTransactionAmount( movement.getTransactionAmount() );
        movementReportDto.setMovementAvailableBalance( movement.getMovementAvailableBalance() );

        return movementReportDto;
    }

    @Override
    public MovementReport toDomain(MovementReportDto movement) {
        if ( movement == null ) {
            return null;
        }

        MovementReport.MovementReportBuilder movementReport = MovementReport.builder();

        movementReport.date( movement.getDate() );
        movementReport.customerName( movement.getCustomerName() );
        movementReport.accountNumber( movement.getAccountNumber() );
        movementReport.accountType( movement.getAccountType() );
        movementReport.availableBalance( movement.getAvailableBalance() );
        movementReport.status( movement.getStatus() );
        movementReport.transactionAmount( movement.getTransactionAmount() );
        movementReport.movementAvailableBalance( movement.getMovementAvailableBalance() );

        return movementReport.build();
    }
}
