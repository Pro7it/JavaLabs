package com.webApplication.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.webApplication.web.entity.OrderDTO;

@RepositoryRestResource
public interface MealOrderRepo extends JpaRepository<OrderDTO, Integer>{
}
