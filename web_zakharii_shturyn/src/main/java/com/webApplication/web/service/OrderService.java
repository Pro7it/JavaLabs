package com.webApplication.web.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webApplication.web.entity.CustomPizza;
import com.webApplication.web.entity.MealDTO;
import com.webApplication.web.entity.Order;
import com.webApplication.web.entity.OrderDTO;
import com.webApplication.web.entity.OrderList;
import com.webApplication.web.repository.MealOrderRepo;
import com.webApplication.web.repository.MealRepo;

@Service
public class OrderService {

    private final MealRepo mealRepo;
    private final MealOrderRepo mealOrderRepo;
    private final CustomPizzaService pizzaBuilder;

    @Autowired
    public OrderService(MealRepo mealRepo, MealOrderRepo mealOrderRepo, CustomPizzaService pizzaBuilder) {
        this.mealRepo = mealRepo;
        this.mealOrderRepo = mealOrderRepo;
        this.pizzaBuilder = pizzaBuilder;
    }

    public void findAllMeal(Model model) {
        model.addAttribute("meals", mealRepo.findAll());
    }

    public Integer getOrderTime(OrderList meals) {
        Integer totalTime = 0;
        for(Order meal : meals.getOrders()) {
            if(meal.getQuantity() > 0) {
                Optional<MealDTO> curMeal = mealRepo.findById(meal.getId());
                if(curMeal.isPresent()) totalTime += curMeal.get().getTimetoprepare();
                else throw new NoSuchElementException();
            }
        }

        if(meals.getCustomPizzaIngredients() != null) {
            totalTime += 20;
        }

        return totalTime;
    }

    public Integer getPriceToGet(OrderList meals) {
        Integer curPrice = 0;
        for(Order meal : meals.getOrders()) {
            if(meal.getQuantity() > 0) {
                Optional<MealDTO> curMeal = mealRepo.findById(meal.getId());
                Integer pricetopay = curMeal.get().getPricetopay();
                Integer pricetocook = curMeal.get().getPricetocook();
                curPrice += (pricetopay - pricetocook) * meal.getQuantity();
            }
        }

        if(meals.getCustomPizzaIngredients() != null) {
            pizzaBuilder.addIngredients(meals.getCustomPizzaIngredients());
            pizzaBuilder.calculateTotalPrice();
            CustomPizza pizza = pizzaBuilder.build();
    
            curPrice += pizza.getPrice();
            pizzaBuilder.reset();
        }


        if(meals.isDelivery()) curPrice += 99;

        return curPrice;
    }

    public long calculateProgress(OrderDTO order) {
        long totalSeconds = order.getTimeToPrepare();
        long doneTime = Duration.between(order.getStartTime(), LocalTime.now()).toSeconds();
        return (int) Math.min(100, (doneTime * 100) / totalSeconds);
    }

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> list = mealOrderRepo.findAll();
        return list;
    }

    public void saveOrder(OrderDTO order) {
        mealOrderRepo.save(order);
    }

    public Optional<OrderDTO> findById(Integer id) {
        return mealOrderRepo.findById(id);
    }

    public void deleteById(Integer id) {
        mealOrderRepo.deleteById(id);
    }
}
