package com.example.finance.controller;

import com.example.finance.model.Transaction;
import com.example.finance.model.Category;
import com.example.finance.model.User;
import com.example.finance.service.CategoryService;
import com.example.finance.service.TransactionService;
import com.example.finance.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;



    @GetMapping("/new")
    public String showTransactionForm(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:users/login";
        }
        User user = userService.findById(userId);
        System.out.println("User Name showTransactionForm: " + user.getUsername());
        System.out.println("User Name showTransactionForm: " + user.getUsername());
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("user", user);
        return "transaction_entry";
    }

    @PostMapping
    public String saveTransaction(@ModelAttribute Transaction transaction,HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:users/login";
        }
        Category category = categoryService.findById(transaction.getCategory().getId());
        User user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        transaction.setCategory(category);
        transaction.setUser(user);
        System.out.println(transaction.getDate());
        transactionService.save(transaction);
        return "redirect:transactions/history";
    }

    @GetMapping("/history")
    public String showTransactionHistory(Model model,HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:users/login";
        }
        model.addAttribute("transactions", transactionService.getTransactionsByUserId(userId));
        return "transaction_history";
    }
}