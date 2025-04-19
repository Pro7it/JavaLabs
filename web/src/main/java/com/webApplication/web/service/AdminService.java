package com.webApplication.web.service;

import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public static Boolean isAdmin = false;

    public static Boolean getAdmin() {
        return isAdmin;
    }

    public static void setAdmin(Boolean newStatus) {
        isAdmin = newStatus;
    }
}
