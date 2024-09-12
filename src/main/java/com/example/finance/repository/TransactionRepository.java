package com.example.finance.repository;

import com.example.finance.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop10ByOrderByDateDesc();
    List<Transaction> findByUserId(Long userId);
    BigDecimal getTotalIncomeByUserId(Long userId);
    BigDecimal getTotalExpensesByUserId(Long userId);
}