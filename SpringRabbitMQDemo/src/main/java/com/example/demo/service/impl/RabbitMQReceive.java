package com.example.demo.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

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
	public void receivePublish1(Message message, Channel channel) throws IOException {
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		System.out.println("rabbitmq.publishQueueA 接收消息：" + new String(message.getBody()));
	}

	@RabbitHandler
	@RabbitListener(queues = "publishQueueB")
	public void receivePublish2(Message message, Channel channel) throws IOException {
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		System.out.println("rabbitmq.publishQueueB 接收消息：" + new String(message.getBody()));
	}

	@RabbitHandler
	@RabbitListener(queues = "headersQueueA")
	public void receiveHeadersA(Message message, Channel channel) {
		if (message.getMessageProperties().getHeaders().containsKey("debug")) {
			try {
				channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
				System.out.println("debug消息直接丢弃！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (message.getMessageProperties().getHeaders().containsKey("info")) {
			try {
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
				System.out.println("rabbitmq.publishQueueC 接收消息：" + new String(message.getBody()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RabbitHandler
	@RabbitListener(queues = "headersQueueB")
	public void receiveHeadersB(Message message, Channel channel, @Headers Map<String, Object> header) {
		if (header.containsKey("error")) {
			try {
				channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
				System.out.println("错误消息需要重新确认！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}