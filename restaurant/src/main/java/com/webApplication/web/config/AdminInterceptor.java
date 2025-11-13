package com.webApplication.web.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.webApplication.web.service.AdminService;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    private final AdminService adminService;

    public AdminInterceptor(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!adminService.isAdmin()) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
