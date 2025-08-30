package com.alcampoverde.ms_transactions.infraestructure.out.persistence.mapper;

import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.entity.AccountEntity;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.entity.MovementEntity;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
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
public class IAccountMovementRepositoryMapperImpl implements IAccountMovementRepositoryMapper {

    private final DatatypeFactory datatypeFactory;

    public IAccountMovementRepositoryMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public Movement toDomain(MovementEntity account) {
        if ( account == null ) {
            return null;
        }

        Movement.MovementBuilder movement = Movement.builder();

        movement.movementId( account.getMovementId() );
        movement.date( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( account.getDate() ) ) );
        movement.movementType( account.getMovementType() );
        if ( account.getInitialBalance() != null ) {
            movement.initialBalance( account.getInitialBalance().doubleValue() );
        }
        if ( account.getTransactionAmount() != null ) {
            movement.transactionAmount( account.getTransactionAmount().doubleValue() );
        }
        if ( account.getAvailableBalance() != null ) {
            movement.availableBalance( account.getAvailableBalance().doubleValue() );
        }
        movement.account( accountEntityToAccount( account.getAccount() ) );
        movement.status( account.getStatus() );

        return movement.build();
    }

    @Override
    public MovementEntity toEntity(Movement account) {
        if ( account == null ) {
            return null;
        }

        MovementEntity movementEntity = new MovementEntity();

        movementEntity.setMovementId( account.getMovementId() );
        movementEntity.setDate( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( account.getDate() ) ) );
        movementEntity.setMovementType( account.getMovementType() );
        if ( account.getInitialBalance() != null ) {
            movementEntity.setInitialBalance( BigDecimal.valueOf( account.getInitialBalance() ) );
        }
        if ( account.getTransactionAmount() != null ) {
            movementEntity.setTransactionAmount( BigDecimal.valueOf( account.getTransactionAmount() ) );
        }
        if ( account.getAvailableBalance() != null ) {
            movementEntity.setAvailableBalance( BigDecimal.valueOf( account.getAvailableBalance() ) );
        }
        movementEntity.setAccount( accountToAccountEntity( account.getAccount() ) );
        movementEntity.setStatus( account.getStatus() );

        return movementEntity;
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

    protected List<Movement> movementEntityListToMovementList(List<MovementEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Movement> list1 = new ArrayList<Movement>( list.size() );
        for ( MovementEntity movementEntity : list ) {
            list1.add( toDomain( movementEntity ) );
        }

        return list1;
    }

    protected Account accountEntityToAccount(AccountEntity accountEntity) {
        if ( accountEntity == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.accountId( accountEntity.getAccountId() );
        account.accountNumber( accountEntity.getAccountNumber() );
        account.accountType( accountEntity.getAccountType() );
        account.availableBalance( accountEntity.getAvailableBalance() );
        account.status( accountEntity.getStatus() );
        account.customerId( accountEntity.getCustomerId() );
        account.movements( movementEntityListToMovementList( accountEntity.getMovements() ) );

        return account.build();
    }

    protected List<MovementEntity> movementListToMovementEntityList(List<Movement> list) {
        if ( list == null ) {
            return null;
        }

        List<MovementEntity> list1 = new ArrayList<MovementEntity>( list.size() );
        for ( Movement movement : list ) {
            list1.add( toEntity( movement ) );
        }

        return list1;
    }

    protected AccountEntity accountToAccountEntity(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setAccountId( account.getAccountId() );
        accountEntity.setAccountNumber( account.getAccountNumber() );
        accountEntity.setAccountType( account.getAccountType() );
        accountEntity.setAvailableBalance( account.getAvailableBalance() );
        accountEntity.setStatus( account.getStatus() );
        accountEntity.setCustomerId( account.getCustomerId() );
        accountEntity.setMovements( movementListToMovementEntityList( account.getMovements() ) );

        return accountEntity;
    }
}
