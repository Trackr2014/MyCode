package com.spring.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.constant.RabbitMQConst;
import com.spring.demo.util.QueueUtil;
import com.spring.demo.util.RabbitMQUtil;

@RestController
public class RabbitMQTestController {
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQTestController.class);
	
	@Autowired
	private QueueUtil queueUtil;
	
	@Autowired
	private RabbitMQUtil rabbitUtil;
	
	@RequestMapping("/rabbit-mq/test")
	public String test(){
		logger.info("rabbitMQ test queue ---> begin");
		queueUtil.push(RabbitMQConst.TEST_QUEUE, "helloWorld");
		return "test";
	}
	
	@RequestMapping("/rabbit/test2")
	public String test2(){
		for(int i=0;i<10000;i++){
			rabbitUtil.sendToQueue(RabbitMQConst.TEST_QUEUE, "ZHESHIGEtest"+i);
		}
		return "test2";
	}
}
