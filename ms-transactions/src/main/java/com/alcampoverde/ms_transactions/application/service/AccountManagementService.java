package com.alcampoverde.ms_transactions.application.service;


import com.alcampoverde.ms_transactions.application.exception.AccountNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.CustomerNotFoundException;
import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.port.in.IAccountManagementPort;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IRequestMessagePort;

import java.util.List;

public class AccountManagementService implements IAccountManagementPort {

    private final IAccountRepositoryPort accountRepoPort;
    private final IRequestMessagePort customerMessagePort;

    public AccountManagementService(IAccountRepositoryPort accountRepoPort, IRequestMessagePort customerMessagePort) {
        this.accountRepoPort = accountRepoPort;
        this.customerMessagePort = customerMessagePort;
    }


    @Override
    public List<Account> findAllAccount() {
        return this.accountRepoPort.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer id) {
        return this.accountRepoPort.findAccountById(id).orElseThrow(() -> new AccountNotFoundException("NO ACCOUNT FOUND"));
    }

    @Override
    public Account findAccountNumberById(String accountNumber) {
        return this.accountRepoPort.findAccountNumberById(accountNumber).orElseThrow(() -> new AccountNotFoundException("NO ACCOUNT FOUND"));

    }

    @Override
    public Account saveAccount(Account account) {
        String customerExists = customerMessagePort.sendMessage(account.getCustomerId().toString());
        if (customerExists == null || customerExists.isEmpty() || customerExists.equalsIgnoreCase("false")) {
            throw new CustomerNotFoundException("No customer found with ID: " + account.getCustomerId());
        }
        return this.accountRepoPort.saveAccount(account);
    }

    @Override
    public Account updateAccount(Account account) {
        validateCustomerExists(account.getCustomerId());
        Account existingAccount = getExistingAccount(account.getAccountId());

        Account updatedAccount = buildUpdatedAccount(existingAccount, account);

        return accountRepoPort.updateAccount(updatedAccount);
    }

    @Override
    public void deleteAccount(Integer id) {
        this.accountRepoPort.deleteAccount(id);
    }

    private void validateCustomerExists(Integer customerId) {
        String customerExists = customerMessagePort.sendMessage(customerId.toString());
        if (customerExists == null || customerExists.isEmpty() || customerExists.equalsIgnoreCase("false")) {
            throw new CustomerNotFoundException("No customer found with ID: " + customerId);
        }
    }

    private Account getExistingAccount(Integer accountId) {
        return accountRepoPort.findAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("No account found with ID: " + accountId));
    }

    private Account buildUpdatedAccount(Account existing, Account newData) {
        return Account.builder().accountId(existing.getAccountId()).accountNumber(newData.getAccountNumber()).accountType(newData.getAccountType()).availableBalance(newData.getAvailableBalance()).status(newData.getStatus()).customerId(newData.getCustomerId()).movements(newData.getMovements()).build();
    }


}
