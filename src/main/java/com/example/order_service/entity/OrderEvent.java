package com.example.order_service.entity;


import com.example.order_service.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("OrderEvent")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@EntityScan
@Builder
public class OrderEvent {
    @Id
    private String orderEventId;
    private String eventId;
    private int eventStep;
    private EventStatus eventStatus;

}
