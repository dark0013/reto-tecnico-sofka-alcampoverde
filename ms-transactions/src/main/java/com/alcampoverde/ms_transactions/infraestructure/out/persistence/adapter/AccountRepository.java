package com.alcampoverde.ms_transactions.infraestructure.out.persistence.adapter;


import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountRepositoryPort;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.entity.AccountEntity;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.mapper.IAccountRepositoryMapper;
import com.alcampoverde.ms_transactions.infraestructure.out.persistence.repository.IAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AccountRepository implements IAccountRepositoryPort {

    private final IAccountRepository accountRepository;
    private final IAccountRepositoryMapper accountMapper;

    public AccountRepository(IAccountRepository accountRepository, IAccountRepositoryMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<Account> findAllAccount() {
        List<AccountEntity> accountEntity = accountRepository.findAll();
        return accountEntity.stream()
                .map(accountMapper::toDomain)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Account> findAccountById(Integer accountId) {
        return accountRepository.findById(accountId).map(accountMapper::toDomain);
    }

    @Override
    public Optional<Account> findAccountNumberById(String accountNumber) {
        return accountRepository
                .findByAccountNumber(accountNumber)
                .map(accountMapper::toDomain);
    }

    @Override
    public Account saveAccount(Account account) {
        AccountEntity saveEntity = accountRepository.save(accountMapper.toEntity(account));
        return accountMapper.toDomain(saveEntity);
    }

    @Override
    public Account updateAccount(Account account) {
        AccountEntity saveEntity = accountRepository.save(accountMapper.toEntity(account));
        return accountMapper.toDomain(saveEntity);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountRepository.deleteById(accountId);
    }

}
