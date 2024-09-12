package com.example.finance.controller;

import com.example.finance.service.DashboardService;
import com.example.finance.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/users/login";
        }
        // Pass user information to the view
        model.addAttribute("totalIncome", dashboardService.getTotalIncomeByUserId(userId));
        model.addAttribute("totalExpenses", dashboardService.getTotalExpensesByUserId(userId));
        model.addAttribute("recentTransactions", transactionService.getTransactionsByUserId(userId));
        return "dashboard";
    }
}