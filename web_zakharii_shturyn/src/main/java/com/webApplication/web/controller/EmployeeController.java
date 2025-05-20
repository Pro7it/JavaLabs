package com.webApplication.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webApplication.web.entity.Employee;
import com.webApplication.web.service.RestaurantFacade;

@Controller
public class EmployeeController {

    private final RestaurantFacade facade;

    @Autowired
    public EmployeeController(RestaurantFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/createEmployee")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "createEmployee";
    }

    @PostMapping("/createEmployee")
    public String submitEmployee(@ModelAttribute Employee employee) {
        facade.saveEmployee(employee);
        return "redirect:/createEmployee?success";
    }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        facade.findAllEmployees(model);
        return "employees";
    }

    @PostMapping("/delete/{employee_id}")   
    public String deleteEmployee(@PathVariable("employee_id") Integer employee_id) {
        facade.deleteEmployee(employee_id);
        return "redirect:/employees";
    }
}
