package com.alcampoverde.ms_transactions.application.service;


import com.alcampoverde.ms_transactions.application.exception.AccountMovementNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.AccountNotFoundException;
import com.alcampoverde.ms_transactions.application.exception.InsufficientBalanceException;
import com.alcampoverde.ms_transactions.application.exception.InvalidMovementTypeException;
import com.alcampoverde.ms_transactions.domain.model.Account;
import com.alcampoverde.ms_transactions.domain.model.Movement;
import com.alcampoverde.ms_transactions.domain.model.MovementReport;
import com.alcampoverde.ms_transactions.domain.port.in.IAccountMovementServicePort;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountMovementRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IAccountRepositoryPort;
import com.alcampoverde.ms_transactions.domain.port.out.IMovementReportRepositoryPort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class MovementService implements IAccountMovementServicePort {

    private final IAccountMovementRepositoryPort accountMovementRepository;
    private final IAccountRepositoryPort accountRepository;
    private final IMovementReportRepositoryPort accountReportRepository;

    public MovementService(IAccountMovementRepositoryPort accountMovementRepository, IAccountRepositoryPort accountRepository,IMovementReportRepositoryPort accountReportRepository) {
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

    @Override
    public Movement transaction(Movement movement) {
        Account account = accountRepository.findAccountById(movement.getAccount().getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + movement.getAccount().getAccountId()));


        Double currentBalance = account.getAvailableBalance();
        Double movementValue = movement.getTransactionAmount();


        if ("WITHDRAWAL".equalsIgnoreCase(movement.getMovementType())) {
            if (movementValue > currentBalance) {
                throw new InsufficientBalanceException("Insufficient balance");
            }
            currentBalance -= movementValue;
            movement.setAvailableBalance(-movementValue);
        } else if ("DEPOSIT".equalsIgnoreCase(movement.getMovementType())) {
            currentBalance += movementValue;
        } else {
            throw new InvalidMovementTypeException("Invalid movement type: " + movement.getMovementType());
        }


        account.setAvailableBalance(currentBalance);
        accountRepository.saveAccount(account);


        movement.setAvailableBalance(currentBalance);
        movement.setDate(LocalDateTime.now());

        return accountMovementRepository.transaction(movement);
    }

    @Override
    public void deactivateMovement(Integer movementId) {
        accountMovementRepository.deactivateMovement(movementId);
    }

    @Override
    public List<MovementReport> findByAccountIdAndDate(Integer accountId,LocalDate startDate, LocalDate endDate) {
        return accountReportRepository.findByAccountIdAndDate(accountId,startDate,endDate);
    }
}
