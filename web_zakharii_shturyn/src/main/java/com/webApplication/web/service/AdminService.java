package com.webApplication.web.service;

import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private boolean isAdmin = false;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void login() {
        isAdmin = true;
    }

    public void logout() {
        isAdmin = false;
    }
}
