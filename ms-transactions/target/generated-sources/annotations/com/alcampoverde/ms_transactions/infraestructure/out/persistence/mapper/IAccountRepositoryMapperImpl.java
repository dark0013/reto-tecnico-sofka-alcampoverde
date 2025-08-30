package com.alcampoverde.ms_transactions.infraestructure.out.persistence.mapper;

import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.entity.AccountEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T05:26:34-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class IAccountRepositoryMapperImpl implements IAccountRepositoryMapper {

    @Override
    public Account toDomain(AccountEntity account) {
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
    public AccountEntity toEntity(Account account) {
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

        return accountEntity;
    }
}
