package com.example.order_service.controller;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderRequestDTO requestDTO){
        requestDTO.setOrderId(UUID.randomUUID());
        return this.service.createOrder(requestDTO);
    }

    @GetMapping("/all")
    public List<Order> getOrders(){
        return this.service.getAll();
    }

}
