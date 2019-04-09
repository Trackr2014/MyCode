package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceive {
	
	@RabbitHandler
	@RabbitListener(queues = "hello")
	public void receive(@Payload String str) {
		System.out.println("消费者接收到消息:" + str);
	}

	@RabbitHandler
	@RabbitListener(queues = "rabbitmq.topic1")
	public void receiveTopic1(String str) {
		System.out.println("rabbitmq.topic1 接收消息：" + str);
	}

	@RabbitHandler
	@RabbitListener(queues = "rabbitmq.topic2")
	public void receiveTopic2(String str) {
		double aaa = 1.2333333333;
		BigDecimal bigDecimal = new BigDecimal(aaa).setScale(0, RoundingMode.UP);
		aaa = bigDecimal.doubleValue();
		System.out.println("rabbitmq.topic2 接收消息：" + aaa);
		System.out.println("rabbitmq.topic2 接收消息：" + str);
	}

	@RabbitHandler
	@RabbitListener(queues = "publishQueueA")
	public void receivePublish1(String str) {
		System.out.println("rabbitmq.publishQueueA 接收消息：" + str);
	}

	@RabbitHandler
	@RabbitListener(queues = "publishQueueB")
	public void receivePublish2(String str) {
		System.out.println("rabbitmq.publishQueueB 接收消息：" + str);
	}

	@RabbitHandler
	@RabbitListener(queues = "publishQueueC")
	public void receivePublish3(String str) {
		System.out.println("rabbitmq.publishQueueC 接收消息：" + str);
	}
}