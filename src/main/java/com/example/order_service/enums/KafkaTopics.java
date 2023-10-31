package com.example.order_service.enums;


public enum KafkaTopics {
    PAYMENT_REQUEST("payment_request");

    private final String topicName;

    KafkaTopics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
