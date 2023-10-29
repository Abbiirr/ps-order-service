package com.example.order_service.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import com.example.order_service.enums.OrderStatus;

import java.util.UUID;

@RedisHash("Order")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EntityScan
public class Order {
    @Id
    private UUID id;
    private String productId;
    private String userId;
    private OrderStatus status;
    private Double price;

}
