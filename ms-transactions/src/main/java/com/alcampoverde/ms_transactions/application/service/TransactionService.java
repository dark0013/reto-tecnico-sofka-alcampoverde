package com.alcampoverde.ms_transactions.application.service;


import com.alcampoverde.ms_transactions.application.exception.AccountMovementNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.AccountNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.InsufficientBalanceException;
import com.alcampoverde.ms_transactions.application.exception.InvalidMovementTypeException;
import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.domain.port.in.IAccountTransactionPort;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountMovementRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IMovementReportRepositoryPort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class TransactionService implements IAccountTransactionPort {

    private final IAccountMovementRepositoryPort accountMovementRepository;
    private final IAccountRepositoryPort accountRepository;
    private final IMovementReportRepositoryPort accountReportRepository;

    public TransactionService(IAccountMovementRepositoryPort accountMovementRepository, IAccountRepositoryPort accountRepository, IMovementReportRepositoryPort accountReportRepository) {
        this.accountMovementRepository = accountMovementRepository;
        this.accountRepository = accountRepository;
        this.accountReportRepository = accountReportRepository;
    }

    @Override
    public Movement findById(Integer id) {
        return accountMovementRepository.findById(id).orElseThrow(() -> new AccountMovementNotFoundException("Movement not found with id: " + id));
    }

    @Override
    public List<Movement> findAll() {
        return accountMovementRepository.findAll();
    }


    public Movement transaction(Movement movement) {
        Account account = getAccountOrThrow(movement.getAccount().getAccountId());

        Double updatedBalance = processMovement(account, movement);

        updateAccountBalance(account, updatedBalance);

        prepareMovement(movement, updatedBalance);

        return accountMovementRepository.transaction(movement);
    }

    private Account getAccountOrThrow(Integer accountId) {
        return accountRepository.findAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));
    }

    private Double processMovement(Account account, Movement movement) {
        double currentBalance = account.getAvailableBalance();
        double movementValue = movement.getTransactionAmount();

        switch (movement.getMovementType().toUpperCase()) {
            case "WITHDRAWAL":
                if (movementValue > currentBalance) {
                    throw new InsufficientBalanceException("Insufficient balance");
                }
                currentBalance -= movementValue;
                movement.setAvailableBalance(-movementValue);
                break;

            case "DEPOSIT":
                currentBalance += movementValue;
                movement.setAvailableBalance(movementValue);
                break;

            default:
                throw new InvalidMovementTypeException("Invalid movement type: " + movement.getMovementType());
        }

        return currentBalance;
    }

    private void updateAccountBalance(Account account, Double updatedBalance) {
        account.setAvailableBalance(updatedBalance);
        accountRepository.saveAccount(account);
    }

    private void prepareMovement(Movement movement, Double updatedBalance) {
        movement.setAvailableBalance(updatedBalance);
        movement.setDate(LocalDateTime.now());
    }


    @Override
    public void cancelTransaction(Integer movementId) {
        accountMovementRepository.cancelTransaction(movementId);
    }

    @Override
    public List<MovementReport> generateMovementReport(Integer accountId, LocalDate startDate, LocalDate endDate) {
        return accountReportRepository.findByAccountIdAndDate(accountId, startDate, endDate);
    }
}
