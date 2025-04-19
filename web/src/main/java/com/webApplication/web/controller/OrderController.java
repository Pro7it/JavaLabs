package com.webApplication.web.controller;

import java.time.LocalTime;
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
import com.webApplication.web.service.AdminService;
import com.webApplication.web.service.EmployeeService;
import com.webApplication.web.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final EmployeeService employeeService;

    @Autowired
    public OrderController(OrderService orderService, EmployeeService employeeService) {
        this.orderService = orderService;
        this.employeeService = employeeService;
    }

    @GetMapping("/menu")
    public String showMenu(Model model) {
        if(AdminService.getAdmin()) {
            orderService.findAllMeal(model);
            return "menu";
        } 
        
        return "redirect:login?failure";
    }

    @PostMapping("/createOrder")
    public String createOrder(@ModelAttribute OrderList meals,RedirectAttributes model) {
        if(AdminService.getAdmin()) {
            if(!employeeService.findChef()) {
                model.addFlashAttribute("alert", "No employees with position: Chef");
                return "redirect:menu";
            }

            if(meals.isDelivery()) {
                if(!employeeService.findDeliveryMan()) {
                    model.addFlashAttribute("alert", "No employees with position: Delivery man");
                    return "redirect:menu";
                }
            } else {
                if(!employeeService.findWaiter()) {
                    model.addFlashAttribute("alert", "No employees with position: Waiter");
                    return "redirect:menu";
                }
            }

            Integer totalTime = orderService.getOrderTime(meals);
            Integer pricetoget = orderService.getPriceToGet(meals);

            OrderDTO order = new OrderDTO(LocalTime.now(), pricetoget, totalTime);
            orderService.saveOrder(order);
            // System.out.println(totalTime + " " + pricetoget);
            return "redirect:home";
        }

        return "redirect:login?failure";
    }

    @GetMapping("/order/{id}")
    public String showOrder(@PathVariable Integer id, Model model) {
        if(AdminService.getAdmin()) {
            Optional<OrderDTO> order = orderService.findById(id);
            // System.out.println(order.get().getTimeToPrepare());
            long progress = orderService.calculateProgress(order.get());
            model.addAttribute("order", order.get());
            model.addAttribute("progress", progress);
            return "order";
        }

        return "redirect:login?failure";
    }

    @PostMapping("/order/{id}/complete")
    public String closeOrder(@PathVariable Integer id) {
        if(AdminService.getAdmin()) {
            orderService.deleteById(id);
            return "redirect:/home";
        }

        return "redirect:login?failure";
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        if(AdminService.getAdmin()) {
            List<OrderDTO> orders = orderService.getAllOrders();
            model.addAttribute("orders", orders);
            return "home";
        }

        return "redirect:login?failure";
    }
}
