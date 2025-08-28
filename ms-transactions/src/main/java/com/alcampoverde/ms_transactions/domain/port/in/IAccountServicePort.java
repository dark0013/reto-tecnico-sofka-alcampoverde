package com.alcampoverde.ms_transactions.domain.port.in;


import com.alcampoverde.ms_transactions.domain.model.Account;

import java.util.List;

public interface IAccountServicePort {

    List<Account> findAllAccount();

    Account findAccountById(Integer id);

    Account findAccountNumberById(String accountNumber);

    Account saveAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(Integer id);
}

