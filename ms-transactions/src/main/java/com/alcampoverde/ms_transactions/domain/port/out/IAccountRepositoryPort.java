package com.alcampoverde.ms_transactions.domain.port.out;

import com.alcampoverde.ms_transactions.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountRepositoryPort {

    List<Account> findAllAccount();

    Optional<Account> findAccountById(Integer id);

    Optional<Account> findAccountNumberById(String accountNumber);

    Account saveAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(Integer id);
}