package com.example.finance.service;

import com.example.finance.model.Transaction;
import com.example.finance.model.TransactionType;
import com.example.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public DashboardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public BigDecimal getTotalIncome() {
        return transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getType() == TransactionType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenses() {
        return transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getRecentTransactions() {
        return transactionRepository.findTop10ByOrderByDateDesc();
    }
}