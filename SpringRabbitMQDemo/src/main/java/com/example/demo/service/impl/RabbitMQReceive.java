package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceive {
	
	@Autowired
	ConnectionFactory connectionFactory;
	
//	@Bean
//	public SimpleMessageListenerContainer messageContainer() {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//		container.setExposeListenerChannel(true);
//		container.setMaxConcurrentConsumers(1);
//		container.setConcurrentConsumers(1);
//		container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
//		container.setMessageListener(new ChannelAwareMessageListener() {
//
//			@Override
//			public void onMessage(Message message, Channel channel) throws Exception {
//				byte[] body = message.getBody();
//				System.out.println("receive msg : " + new String(body));
//				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 确认消息成功消费
//			}
//		});
//		return container;
//	}
	
	
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