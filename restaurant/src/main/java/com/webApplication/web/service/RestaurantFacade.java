package com.webApplication.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webApplication.web.command.CloseOrderCommand;
import com.webApplication.web.command.Command;
import com.webApplication.web.command.CreateOrderCommand;
import com.webApplication.web.entity.Employee;
import com.webApplication.web.entity.OrderDTO;
import com.webApplication.web.entity.OrderList;

@Service
public class RestaurantFacade {

    private final EmployeeService employeeService;
    private final OrderService orderService;
    private final BalanceService balanceService;
    
    public RestaurantFacade(EmployeeService employeeService, OrderService orderService, 
        BalanceService balanceService) {
        this.employeeService = employeeService;
        this.orderService = orderService;
        this.balanceService = balanceService;
    }

    // Для кращого розуміння я собі їх в купки поліпив
    // Загалом ідея - Йди до того і поверни то, що той повертає
    // Чудо перевірка чи всі на місці
    public boolean hasChef() {
        return employeeService.findChef();
    }
    public boolean hasWaiter() {
        return employeeService.findWaiter();
    }
    public boolean hasDeliveryMan() {
        return employeeService.findDeliveryMan();
    }
    public boolean checkEmployees(OrderList orderList) {
        if(orderList.isDelivery()) return hasChef() && hasDeliveryMan();
        else return hasChef() && hasWaiter();
    }

    // Трішки база даних з робочими
    public void saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
    }
    public void deleteEmployee(Integer id) {
        employeeService.deleteEmployee(id);
    }
    public void findAllEmployees(Model model) {
        employeeService.findAllEmployees(model);
    }

    // Величезнеееее опрацювання замовлень
    public Integer getPriceToGet(OrderList orderList) {
        return orderService.getPriceToGet(orderList);
    }
    public Integer gerOrderTime(OrderList orderList) {
        return orderService.getOrderTime(orderList);
    }
    public void saveOrder(OrderDTO order) {
        orderService.saveOrder(order);
    }   
    public void findAllMeal(Model model) {
        orderService.findAllMeal(model);
    }
    public Optional<OrderDTO> findById(int id) {
        return orderService.findById(id);
    }
    public long calculateProgress(OrderDTO order) {
        return orderService.calculateProgress(order);
    }
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
    public void deleteById(Integer id) {
        orderService.deleteById(id);
    }
    public Integer calculateSalary() {
        return employeeService.calculateSalary();
    }
    public void createOrder(OrderList orderList, RedirectAttributes model) {
        Command command = new CreateOrderCommand(this, model, orderList);
        command.execute();
    }
    public void closeOrder(Integer id) {
        Command command = new CloseOrderCommand(this, id);
        command.execute();
    }

    // Ну і чучуть фінансів
    public void addToBalance(Integer amount) {
        balanceService.addToBalance(amount);
    }
    public void removeFromBalance(Integer amount) {
        balanceService.removeFromBalance(amount);
    }
    public Integer getBalance() {
        return balanceService.getBalance();
    }

}
