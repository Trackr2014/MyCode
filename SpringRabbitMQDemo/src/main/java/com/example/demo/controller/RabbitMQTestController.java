package com.example.demo.controller;

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
}
