package com.example.order_service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import com.example.order_service.enums.OrderStatus;

import java.util.HashMap;
import java.util.UUID;

@RedisHash("Order")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EntityScan
@Builder
public class Order {
    @Id
    private UUID id;
    private String userId;

    private HashMap<String, Integer> products;
    private Double totalPrice;
    private OrderStatus status;

}
