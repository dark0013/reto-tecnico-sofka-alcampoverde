package com.alcampoverde.ms_transactions.infraestructure.in.mapper;

import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    @Mapping(target = "movements", ignore = true)
    Account toDomain(AccountDto account);

    AccountDto toDto(Account account);

}

