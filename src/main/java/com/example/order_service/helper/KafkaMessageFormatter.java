package com.example.order_service.helper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaMessageFormatter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

//    public static String formatCheckoutRequest(CheckoutEventDTO requestDTO) throws JsonProcessingException {
//        return objectMapper.writeValueAsString(requestDTO);
//    }
}

