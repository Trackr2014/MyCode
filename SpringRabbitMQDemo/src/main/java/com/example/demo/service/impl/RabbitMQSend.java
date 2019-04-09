package com.example.demo.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.RabbitMQTestService;

@Component
public class RabbitMQSend implements RabbitMQTestService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void sendMsg(String str) {
		rabbitTemplate.convertAndSend("hello", str);
	}	

	@Override
	public void sendTopicMsg(String topicMsgString) {
		rabbitTemplate.convertAndSend("exchangeA", "log.debug", topicMsgString);
	}

	@Override
	public boolean sendPublishMsg(String publishMsgString) {
		CorrelationData cData = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend("publishExchange", "", publishMsgString, cData);
		return true;
	}

	@Override
	public void sendHeadersMsg(Map<String, Object> msg) {
		rabbitTemplate.convertAndSend("headersExchange", null, msg);
	}

	@Override
	public void sendHeadersMsg(Message headerMsgString) {
		rabbitTemplate.convertAndSend("headersExchange", null, headerMsgString);
		
	}	
}
