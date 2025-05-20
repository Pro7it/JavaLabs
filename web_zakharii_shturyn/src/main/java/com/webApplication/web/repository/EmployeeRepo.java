package com.webApplication.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.webApplication.web.entity.Employee;

@RepositoryRestResource
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
    Boolean existsByEmployeePosition(String employeePosition);
}
