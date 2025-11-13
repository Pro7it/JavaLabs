package com.webApplication.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webApplication.web.service.AdminService;

@Controller
public class LoginController {

    private final AdminService adminService;

    public LoginController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes model) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            adminService.login();
            return "redirect:/home";
        } else {
            return "redirect:login?failure";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}

