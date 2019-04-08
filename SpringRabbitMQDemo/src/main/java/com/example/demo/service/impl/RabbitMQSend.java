package com.example.demo.service.impl;

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
		rabbitTemplate.convertAndSend("publishExchange", "", publishMsgString);
		return true;
	}	
}
