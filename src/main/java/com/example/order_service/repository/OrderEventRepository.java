package com.example.order_service.repository;

import com.example.order_service.entity.OrderEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEventRepository extends CrudRepository<OrderEvent, String> {}
