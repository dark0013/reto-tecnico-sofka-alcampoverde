package com.alcampoverde.ms_transactions.application.service;


import com.alcampoverde.ms_transactions.application.exception.AccountNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.CustomerNotFoundException;
import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.port.in.IAccountManagementPort;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IRequestMessagePort;

import java.util.List;
import java.util.Optional;

public class AccountManagementService implements IAccountManagementPort {

    private final IAccountRepositoryPort accountRepository;
    private final IRequestMessagePort requestMessagePort;

    public AccountManagementService(IAccountRepositoryPort accountRepository, IRequestMessagePort requestMessagePort) {
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

    @Override
    public Account updateAccount(Account account) {
        validateCustomerExists(account.getCustomerId());
        Account existingAccount = getExistingAccount(account.getAccountId());

        Account updatedAccount = buildUpdatedAccount(existingAccount, account);

        return accountRepository.updateAccount(updatedAccount);
    }

    @Override
    public void deleteAccount(Integer id) {
        this.accountRepository.deleteAccount(id);
    }

    private void validateCustomerExists(Integer customerId) {
        String customerExists = requestMessagePort.sendMessage(customerId.toString());
        if (customerExists == null || customerExists.isEmpty() || customerExists.equalsIgnoreCase("false")) {
            throw new CustomerNotFoundException("No customer found with ID: " + customerId);
        }
    }

    private Account getExistingAccount(Integer accountId) {
        return accountRepository.findAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("No account found with ID: " + accountId));
    }

    private Account buildUpdatedAccount(Account existing, Account newData) {
        return Account.builder().accountId(existing.getAccountId()).accountNumber(newData.getAccountNumber()).accountType(newData.getAccountType()).availableBalance(newData.getAvailableBalance()).status(newData.getStatus()).customerId(newData.getCustomerId()).movements(newData.getMovements()).build();
    }


}
