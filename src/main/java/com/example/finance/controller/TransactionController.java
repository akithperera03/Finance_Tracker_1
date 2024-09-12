package com.example.finance.controller;

import com.example.finance.model.Transaction;
import com.example.finance.model.Category;
import com.example.finance.model.User;
import com.example.finance.service.CategoryService;
import com.example.finance.service.TransactionService;
import com.example.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public TransactionController(TransactionService transactionService, CategoryService categoryService, UserService userService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionService.findAll());
        return "transaction_history";
    }

    @GetMapping("/new")
    public String showTransactionForm(Model model, @RequestParam Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("user", user);
        return "transaction_entry";
    }

    @PostMapping
    public String saveTransaction(@ModelAttribute Transaction transaction, @RequestParam Long userId) {
        Category category = categoryService.findById(transaction.getCategory().getId());
        User user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        transaction.setCategory(category);
        transaction.setUser(user);
        transactionService.save(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/history")
    public String showTransactionHistory(Model model) {
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("transactions", transactions);
        return "transaction_history";
    }
}