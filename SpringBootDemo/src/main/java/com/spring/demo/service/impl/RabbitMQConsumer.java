package com.spring.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.spring.demo.constant.RabbitMQConst;

@Component
public class RabbitMQConsumer {
	private static final Logger LOG = LoggerFactory.getLogger(RabbitListener.class);
	@RabbitListener(queues=RabbitMQConst.TEST_QUEUE)
	public void testConsumer(@Payload Object msg){
		System.out.print(msg.toString());
		LOG.info("收到消息" + msg.toString());
	}
}
