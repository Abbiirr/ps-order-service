package com.example.order_service.service;

import com.example.order_service.dto.OrderRequestDTO;
import com.example.order_service.dto.OrderResponseDTO;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.order_service.enums.OrderStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    // product price map
    private static final Map<Integer, Double> PRODUCT_PRICE = Map.of(
            1, 100d,
            2, 200d,
            3, 300d
    );

    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private FluxSink<OrchestratorRequestDTO> sink;

    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        Order purchaseOrder = this.orderRepository.save(this.dtoToEntity(orderRequestDTO));
//        this.sink.next(this.getOrchestratorRequestDTO(orderRequestDTO));
        return purchaseOrder;
    }


    public List<Order> getAll() {
        Iterable<Order> users = orderRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toList());

    }

    private Order dtoToEntity(final OrderRequestDTO dto) {
        Order purchaseOrder = new Order();
        purchaseOrder.setId(dto.getOrderId());
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(dto.getPrice());
        return purchaseOrder;
    }

    private OrderResponseDTO entityToDto(final Order purchaseOrder) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(purchaseOrder.getId());
        dto.setProductId(purchaseOrder.getProductId());
        dto.setUserId(purchaseOrder.getUserId());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setAmount(purchaseOrder.getPrice());
        return dto;
    }

//    public OrchestratorRequestDTO getOrchestratorRequestDTO(OrderRequestDTO orderRequestDTO) {
//        OrchestratorRequestDTO requestDTO = new OrchestratorRequestDTO();
//        requestDTO.setUserId(orderRequestDTO.getUserId());
//        requestDTO.setAmount(PRODUCT_PRICE.get(orderRequestDTO.getProductId()));
//        requestDTO.setOrderId(orderRequestDTO.getOrderId());
//        requestDTO.setProductId(orderRequestDTO.getProductId());
//        return requestDTO;
//    }

}
