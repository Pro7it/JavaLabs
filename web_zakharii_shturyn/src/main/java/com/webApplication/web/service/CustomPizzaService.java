package com.webApplication.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.webApplication.web.entity.CustomPizza;

@Service
public class CustomPizzaService {
    private List<String> ingredients = new ArrayList<>();
    private int price = 0;

    public CustomPizzaService addIngredients(List<String> ingredients) {
        for(String topping : ingredients) {
            this.ingredients.add(topping);
        }
        return this;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public int getPizzaPrice() {
        return price;
    }

    public void calculateTotalPrice() {
        price = ingredients.size() * 10;
    }

    public CustomPizza build() {
        return new CustomPizza(this);
    }

    public void reset() {
        ingredients.clear();
        price = 0;
    }
    
}
