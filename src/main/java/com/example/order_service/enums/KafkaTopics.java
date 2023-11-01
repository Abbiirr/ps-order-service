package com.example.order_service.enums;


public enum KafkaTopics {
    PAYMENT_REQUEST("payment_request"),
    GET_ORDER("get_order"),
    POST_GET_ORDER("post_get_order"),;

    private final String topicName;

    KafkaTopics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
