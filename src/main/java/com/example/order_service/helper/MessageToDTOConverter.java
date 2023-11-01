package com.example.order_service.helper;

import com.example.order_service.dto.OrderRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class MessageToDTOConverter {


    public static OrderRequestDTO convertToOrderRequestDTO(String message) {
        return new OrderRequestDTO();
    }
}
