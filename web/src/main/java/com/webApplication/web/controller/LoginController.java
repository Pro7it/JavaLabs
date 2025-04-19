package com.webApplication.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webApplication.web.service.AdminService;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes model) {
        
        if("admin".equals(username) && "admin123".equals(password)) {
            AdminService.setAdmin(true);
            return "redirect:/home";
        } else {
            model.addFlashAttribute("error", "Incorrect username or password");
            return "redirect:login?failure";
        }
    }
}
