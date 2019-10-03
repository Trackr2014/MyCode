package com.example.demo.service;

import java.util.Map;

import org.springframework.amqp.core.Message;

public interface RabbitMQTestService {

    void sendMsg(String str);

    void sendTopicMsg(String topicMsgString);

    boolean sendPublishMsg(String publishMsgString);

    void sendHeadersMsg(Message message);

    void sendHeadersMsg(Map<String, Object> msg);
}
