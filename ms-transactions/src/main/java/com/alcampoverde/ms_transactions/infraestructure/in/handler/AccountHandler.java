package com.alcampoverde.ms_transactions.infraestructure.in.handler;

import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.port.in.IAccountServicePort;
import com.alcampoverde.ms_transactions.infraestructure.in.dto.AccountDto;
import com.alcampoverde.ms_transactions.infraestructure.in.mapper.IAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountHandler {

    private final IAccountServicePort accountService;
    private final IAccountMapper accountMapper;

    public List<AccountDto> findAllAccount() {
        return accountService.findAllAccount()
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }

    public AccountDto findAccountById(Integer id) {
        Account account = accountService.findAccountById(id);
        return accountMapper.toDto(account);
    }

    public AccountDto findAccountNumberById(String accountNumber) {
        Account account = accountService.findAccountNumberById(accountNumber);
        return accountMapper.toDto(account);
    }

    public AccountDto saveAccount(AccountDto account) {
        Account accountObj = accountService.saveAccount(accountMapper.toDomain(account));
        return accountMapper.toDto(accountObj);
    }

    public AccountDto updateAccount(AccountDto account) {
        Account accountObj = accountService.updateAccount(accountMapper.toDomain(account));
        return accountMapper.toDto(accountObj);
    }

    public void deleteAccount(Integer id){
        accountService.deleteAccount(id);
    }

}

