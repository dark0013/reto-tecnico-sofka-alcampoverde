package com.alcampoverde.ms_transactions.application.service;


import com.alcampoverde.ms_transactions.application.exception.AccountNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.CustomerNotFoundException;
import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.port.in.IAccountServicePort;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IRequestMessagePort;

import java.util.List;
import java.util.Optional;

public class AccountService implements IAccountServicePort {

    private final IAccountRepositoryPort accountRepository;
    private final IRequestMessagePort requestMessagePort;

    public AccountService(IAccountRepositoryPort accountRepository, IRequestMessagePort requestMessagePort) {
        this.accountRepository = accountRepository;
        this.requestMessagePort = requestMessagePort;
    }


    @Override
    public List<Account> findAllAccount() {
        return this.accountRepository.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer id) {
        return this.accountRepository.findAccountById(id).orElseThrow(() -> new AccountNotFoundException("NO ACCOUNT FOUND"));
    }

    @Override
    public Account findAccountNumberById(String accountNumber) {
        return this.accountRepository.findAccountNumberById(accountNumber).orElseThrow(() -> new AccountNotFoundException("NO ACCOUNT FOUND"));

    }

    @Override
    public Account saveAccount(Account account) {
        String customerExists = requestMessagePort.sendMessage(account.getCustomerId().toString());
        if (customerExists == null || customerExists.isEmpty() || customerExists.equalsIgnoreCase("false")) {
            throw new CustomerNotFoundException("No customer found with ID: " + account.getCustomerId());
        }
        return this.accountRepository.saveAccount(account);
    }

    public Account updateAccount(Account account) {
        String customerExists = requestMessagePort.sendMessage(account.getCustomerId().toString());
        if (customerExists == null || customerExists.isEmpty() || customerExists.equalsIgnoreCase("false")) {
            throw new CustomerNotFoundException("No customer found with ID: " + account.getCustomerId());
        }
        Optional<Account> existingAccountOpt = accountRepository.findAccountById(account.getAccountId());
        if (existingAccountOpt.isEmpty()) {
            throw new AccountNotFoundException("NO ACCOUNT FOUND");
        }
        Account existingAccount = Account.builder()
                .accountId(account.getAccountId())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .availableBalance(account.getAvailableBalance())
                .status(account.getStatus())
                .customerId(account.getCustomerId())
                .movements(account.getMovements())
                .build();

        return accountRepository.updateAccount(existingAccount);
    }

    @Override
    public void deleteAccount(Integer id) {
        this.accountRepository.deleteAccount(id);
    }

}
