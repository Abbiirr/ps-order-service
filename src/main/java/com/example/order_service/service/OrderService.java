package com.example.order_service.service;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.enums.OrderStatus;
import com.example.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {


    // product price map


    @Autowired
    private OrderRepository orderRepository;


    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        Order purchaseOrder = this.orderRepository.save(Order.builder()
                .id(orderRequestDTO.getOrderId())
                .userId(orderRequestDTO.getUserId())
                .products(orderRequestDTO.getProducts())
                .totalPrice(orderRequestDTO.getTotalPrice())
                .status(OrderStatus.ORDER_CREATED)
                .build());

        return purchaseOrder;
    }


    public List<Order> getAll() {
        Iterable<Order> users = orderRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toList());

    }



}
