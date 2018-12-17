package com.spring.demo.util;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueUtil {
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void push(String routerKey, Object msg){
		rabbitTemplate.convertAndSend(routerKey, msg);
	}
}
