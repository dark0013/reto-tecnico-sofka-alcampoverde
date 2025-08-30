package com.alcampoverde.ms_transactions.infraestructure.in.mapper;

import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.AccountDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T05:26:35-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class IAccountMapperImpl implements IAccountMapper {

    @Override
    public Account toDomain(AccountDto account) {
        if ( account == null ) {
            return null;
        }

        Account.AccountBuilder account1 = Account.builder();

        account1.accountId( account.getAccountId() );
        account1.accountNumber( account.getAccountNumber() );
        account1.accountType( account.getAccountType() );
        account1.availableBalance( account.getAvailableBalance() );
        account1.status( account.getStatus() );
        account1.customerId( account.getCustomerId() );

        return account1.build();
    }

    @Override
    public AccountDto toDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setAccountId( account.getAccountId() );
        accountDto.setAccountNumber( account.getAccountNumber() );
        accountDto.setAccountType( account.getAccountType() );
        accountDto.setAvailableBalance( account.getAvailableBalance() );
        accountDto.setStatus( account.getStatus() );
        accountDto.setCustomerId( account.getCustomerId() );

        return accountDto;
    }
}
