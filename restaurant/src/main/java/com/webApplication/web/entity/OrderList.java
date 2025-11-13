package com.webApplication.web.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderList {
    private List<Order> orders;
    private List<String> customPizzaIngredients;
    private boolean delivery;
}
