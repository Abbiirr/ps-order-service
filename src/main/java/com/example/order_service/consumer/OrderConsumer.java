package com.example.order_service.consumer;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.enums.KafkaTopics;
import com.example.order_service.helper.KafkaMessager;
import com.example.order_service.helper.MessageToDTOConverter;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import com.example.order_service.entity.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
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
    public String checkUserAndOrderListener(String message, Acknowledgment acknowledgment) {
        String orderId = MessageToDTOConverter.getField(message, "orderId");
        if(orderId == null) {
            message = MessageToDTOConverter.addStatus(message, "fail");
        }
        else {
            Optional<Order> order = orderRepository.findById(orderId);
            if (!order.isPresent()) { //if success then return true
                message = MessageToDTOConverter.addStatus(message, "fail");
            }
            message = MessageToDTOConverter.addProductsToMessage(message, order.get().getProducts());
        }
        String response =  kafkaMessager.sendMessage(KafkaTopics.POST_GET_ORDER.getTopicName(), message);
        acknowledgment.acknowledge();   
        return response;

    }
}
