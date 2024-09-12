//package com.example.finance.controller;
//
//import com.example.finance.model.User;
//import com.example.finance.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class LoginController {
//
//    private final UserService userService;
//
//    @Autowired
//    public LoginController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password, Model model) {
//        User user = userService.authenticate(username, password);
//        if (user != null) {
//            return "redirect:/dashboard";
//        } else {
//            model.addAttribute("error", "Invalid username or password");
//            return "login";
//        }
//    }
//}