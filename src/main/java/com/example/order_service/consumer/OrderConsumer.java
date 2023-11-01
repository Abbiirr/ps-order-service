package com.example.order_service.consumer;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.enums.KafkaTopics;
import com.example.order_service.helper.KafkaMessager;
import com.example.order_service.helper.MessageToDTOConverter;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import com.example.order_service.entity.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderConsumer {
    private final KafkaMessager kafkaMessager;
    private final MessageToDTOConverter messageToDTOConverter;

    private final OrderService service;

    private final OrderRepository orderRepository;



    public OrderConsumer(KafkaMessager kafkaMessager, MessageToDTOConverter messageToDTOConverter, OrderService service, OrderRepository orderRepository) {
        this.kafkaMessager = kafkaMessager;
        this.messageToDTOConverter = messageToDTOConverter;
        this.service = service;
        this.orderRepository = orderRepository;
    }


    @KafkaListener(topics = "get_order", groupId = "group_1", containerFactory = "kafkaListenerContainerFactory")
    public String checkUserAndOrderListener(String message) {
        OrderRequestDTO paymentRequestDTO = MessageToDTOConverter.convertToOrderRequestDTO(message);
        Optional<Order> order = orderRepository.findById(paymentRequestDTO.getUserId());
        if(!order.isPresent()){ //if success then return true
            message = "User does not exists";
        }

        return kafkaMessager.sendMessage(KafkaTopics.POST_GET_ORDER.getTopicName(), paymentRequestDTO);

    }
}
