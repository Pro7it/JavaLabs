package com.webApplication.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webApplication.web.entity.Employee;
import com.webApplication.web.repository.EmployeeRepo;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public void deleteEmployee(Integer employee_id) {
        employeeRepo.deleteById(employee_id);
    }

    public void findAllEmployees(Model model) {
        model.addAttribute("employees", employeeRepo.findAll());
    }

    public void saveEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public Boolean findChef() {
        return employeeRepo.existsByEmployeePosition("Chef");
    }
    
    public Boolean findWaiter() {
        return employeeRepo.existsByEmployeePosition("Waiter");
    }
    
    public Boolean findDeliveryMan() {
        return employeeRepo.existsByEmployeePosition("Delivery man");
    }
}
