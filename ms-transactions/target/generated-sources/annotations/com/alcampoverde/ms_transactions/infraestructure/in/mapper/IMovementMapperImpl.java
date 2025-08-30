package com.alcampoverde.ms_transactions.infraestructure.in.mapper;

import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.MovementDto;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T05:26:35-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class IMovementMapperImpl implements IMovementMapper {

    private final DatatypeFactory datatypeFactory;

    public IMovementMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public MovementDto toDto(Movement transaction) {
        if ( transaction == null ) {
            return null;
        }

        MovementDto movementDto = new MovementDto();

        movementDto.setAccountId( transactionAccountAccountId( transaction ) );
        movementDto.setMovementId( transaction.getMovementId() );
        movementDto.setDate( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( transaction.getDate() ) ) );
        movementDto.setMovementType( transaction.getMovementType() );
        if ( transaction.getTransactionAmount() != null ) {
            movementDto.setTransactionAmount( BigDecimal.valueOf( transaction.getTransactionAmount() ) );
        }

        return movementDto;
    }

    @Override
    public Movement toDomain(MovementDto transaction) {
        if ( transaction == null ) {
            return null;
        }

        Movement.MovementBuilder movement = Movement.builder();

        movement.account( movementDtoToAccount( transaction ) );
        movement.movementId( transaction.getMovementId() );
        movement.date( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( transaction.getDate() ) ) );
        movement.movementType( transaction.getMovementType() );
        if ( transaction.getTransactionAmount() != null ) {
            movement.transactionAmount( transaction.getTransactionAmount().doubleValue() );
        }

        return movement.build();
    }

    private XMLGregorianCalendar localDateToXmlGregorianCalendar( LocalDate localDate ) {
        if ( localDate == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendarDate(
            localDate.getYear(),
            localDate.getMonthValue(),
            localDate.getDayOfMonth(),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }

    private static LocalDateTime xmlGregorianCalendarToLocalDateTime( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        if ( xcal.getYear() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMonth() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getDay() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getHour() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMinute() != DatatypeConstants.FIELD_UNDEFINED
        ) {
            if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED
                && xcal.getMillisecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond(),
                    Duration.ofMillis( xcal.getMillisecond() ).getNano()
                );
            }
            else if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond()
                );
            }
            else {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute()
                );
            }
        }
        return null;
    }

    private Integer transactionAccountAccountId(Movement movement) {
        if ( movement == null ) {
            return null;
        }
        Account account = movement.getAccount();
        if ( account == null ) {
            return null;
        }
        Integer accountId = account.getAccountId();
        if ( accountId == null ) {
            return null;
        }
        return accountId;
    }

    protected Account movementDtoToAccount(MovementDto movementDto) {
        if ( movementDto == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.accountId( movementDto.getAccountId() );

        return account.build();
    }
}
