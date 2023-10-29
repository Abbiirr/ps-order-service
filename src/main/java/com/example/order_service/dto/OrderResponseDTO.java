package com.example.order_service.dto;

import com.example.order_service.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private UUID orderId;
    private String userId;
    private String productId;
    private OrderStatus status;
    private Double amount;
}
