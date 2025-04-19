package com.webApplication.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.webApplication.web.service.AdminService;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // @GetMapping("/home")
    // public String showAdminPage() {
    //     if(AdminService.getAdmin()) {
    //         return "home";
    //     }

    //     return "redirect:login?failure";
    // }

    @GetMapping("/logout")
    public String logoutPage(){
        AdminService.setAdmin(false);
        return "redirect:login";
    }
}
