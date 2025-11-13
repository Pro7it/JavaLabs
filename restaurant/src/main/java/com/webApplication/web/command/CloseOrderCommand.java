package com.webApplication.web.command;

import java.util.Optional;

import com.webApplication.web.entity.OrderDTO;
import com.webApplication.web.service.RestaurantFacade;

public class CloseOrderCommand implements Command {
    private final RestaurantFacade facade;
    private final Integer id;

    public CloseOrderCommand(RestaurantFacade facade, Integer id) {
        this.facade = facade;
        this.id = id;
    }

    @Override   
    public void execute() {
        Optional<OrderDTO> orderDB = facade.findById(id);
        if(orderDB.isPresent()) {
            OrderDTO order = orderDB.get();
            Integer priceToGet = order.getPriceToGet();

            Integer salary = facade.calculateSalary();
            facade.addToBalance(priceToGet);
            facade.removeFromBalance(salary);
    
            facade.deleteById(id);
        }
    }

    
}
