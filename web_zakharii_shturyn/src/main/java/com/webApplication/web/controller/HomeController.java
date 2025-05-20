package com.webApplication.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.webApplication.web.service.AdminService;

@Controller
public class HomeController {

    private final AdminService adminService;

    public HomeController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/logout")
    public String logoutPage(){
        adminService.logout();
        return "redirect:/login";
    }
}
