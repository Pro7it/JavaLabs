package com.webApplication.web.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class BalanceService {
    public Integer balance;

    @PostConstruct
    public void init() {
        balance = 1000;
    }

    public void addToBalance(Integer amount) {
        balance += amount;
    }

    public void removeFromBalance(Integer amount) {
        balance -= amount;
        if(balance < 0) balance = 0;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer amount) {
        balance = amount;
    }
}
