package com.webApplication.web.command;

import java.time.LocalTime;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webApplication.web.entity.OrderDTO;
import com.webApplication.web.entity.OrderList;
import com.webApplication.web.service.RestaurantFacade;

public class CreateOrderCommand implements Command{
    private final RestaurantFacade facade;
    private final RedirectAttributes model;
    private final OrderList orderList;

    public CreateOrderCommand(RestaurantFacade facade, RedirectAttributes model, OrderList orderList) {
        this.facade = facade;
        this.model = model;
        this.orderList = orderList;
    }

    @Override
    public void execute() {
        if(!facade.checkEmployees(orderList)) {
            model.addFlashAttribute("alert", "Not enought employees");
            return;
        } 

        Integer price = facade.getPriceToGet(orderList);
        Integer orderTime = facade.gerOrderTime(orderList);
        
        OrderDTO order = new OrderDTO(LocalTime.now(), price, orderTime);
        facade.saveOrder(order);
    }
}
