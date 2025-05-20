package com.webApplication.web.entity;

import java.util.ArrayList;
import java.util.List;

import com.webApplication.web.service.CustomPizzaService;

public class CustomPizza {
    private final List<String> ingredients;
    private int price = 0;

    public CustomPizza(CustomPizzaService builder) {
        this.ingredients = new ArrayList<>(builder.getIngredients());
        this.price = builder.getPizzaPrice();
    }

    public int getPrice() {
        return price; 
    }
    
}
