package com.webApplication.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webApplication.web.entity.OrderDTO;
import com.webApplication.web.entity.OrderList;
import com.webApplication.web.service.RestaurantFacade;

@Controller
public class OrderController {

    private final RestaurantFacade facade;

    @Autowired
    public OrderController(RestaurantFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/menu")
    public String showMenu(Model model) {
        facade.findAllMeal(model);
        return "menu";
    }

    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute OrderList meals,RedirectAttributes model) {
        facade.createOrder(meals, model);
        return "redirect:home";
    }

    @GetMapping("/order/{id}")
    public String showOrder(@PathVariable Integer id, Model model) {
        Optional<OrderDTO> order = facade.findById(id);
        long progress = facade.calculateProgress(order.get());
        model.addAttribute("order", order.get());
        model.addAttribute("progress", progress);
        return "order";
    }

    @PostMapping("/order/{id}/complete")
    public String closeOrder(@PathVariable Integer id) {
        facade.closeOrder(id);
        return "redirect:/home";
    }
    

    @GetMapping("/home")
    public String showHome(Model model) {
        List<OrderDTO> orders = facade.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("balance", facade.getBalance());
        return "home";
    }
}
