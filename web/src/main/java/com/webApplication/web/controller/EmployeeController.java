package com.webApplication.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webApplication.web.entity.Employee;
import com.webApplication.web.service.AdminService;
import com.webApplication.web.service.EmployeeService;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/createEmployee")
    public String showEmployeeForm(Model model) {
        if(AdminService.getAdmin()) {
            model.addAttribute("employee", new Employee());
            return "createEmployee";
        }

        return "redirect:login?failure";
    }

    @PostMapping("/createEmployee")
    public String submitEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/createEmployee?success";
    }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        if(AdminService.getAdmin()) {
            employeeService.findAllEmployees(model);
            return "employees";
        }

        return "redirect:login?failure";
    }

    @PostMapping("/delete/{employee_id}")   
    public String deleteEmployee(@PathVariable("employee_id") Integer employee_id) {
        if(AdminService.getAdmin()) {
            employeeService.deleteEmployee(employee_id);
            return "redirect:/employees";
        }

        return "redirect:login?failure";
    }
}
