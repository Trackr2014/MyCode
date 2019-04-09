package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.RabbitMQTestService;

@RestController
@RequestMapping("/v1/rabbitmq")
public class RabbitMQTestController {

	@Autowired
	RabbitMQTestService rabbitMQTestService;
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String test() {
		System.out.println("生产者发送消息：aaa");
		String string = "aaa";
		rabbitMQTestService.sendMsg(string);
		return "OK12";
	}
	
	@RequestMapping(value="/topic", method=RequestMethod.GET)
	public String testTopic() {
		String topicMsgString = "topic 123";
		System.out.println("topic生产者消息：" + topicMsgString);
		rabbitMQTestService.sendTopicMsg(topicMsgString);
		return "topic send msg successed!";
	}
	
	@RequestMapping(value="/fanout", method=RequestMethod.GET)
	public String testPublish() {
		String publishMsgString = "publish aaa";
		System.out.println("publish生产者消息：" + publishMsgString);
		boolean flag = rabbitMQTestService.sendPublishMsg(publishMsgString);
		if (flag) {
			return "publish send msg successed!";
		} else {
			return "publish send msg fail!";
		}
		
	}
	
	@RequestMapping(value="/header", method=RequestMethod.GET)
	public String testHeaders() {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("debug", "debug");
		msg.put("info", "info");
		msg.put("error", "error");
		MessageProperties messageProperties = new MessageProperties();
		// 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
		messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
		messageProperties.setContentType("UTF-8");
		messageProperties.getHeaders().putAll(msg);
		Message message = new Message("hello,rabbit_headers_any！".getBytes(), messageProperties);
		System.out.println("publish生产者消息：" + new String(message.getBody()));
		rabbitMQTestService.sendHeadersMsg(message);
		return "Headers send msg successed!";
	}
}
