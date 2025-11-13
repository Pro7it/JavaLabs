package com.webApplication.web.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "meal_order")
public class OrderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "start_time")
    private LocalTime startTime;
    
    @Column(name = "time_to_prepare")
    private Integer timeToPrepare;
    
    @Column(name = "price_to_get")
    private Integer priceToGet;

    public OrderDTO() {};


    public OrderDTO(LocalTime starTime, Integer priceToGet, Integer timeToPrepare) {
        this.startTime = starTime;
        this.priceToGet = priceToGet;
        this.timeToPrepare = timeToPrepare;
    }
}
