package com.example.demo.service;

public interface RabbitMQTestService {
	
	void sendMsg(String str);

	void sendTopicMsg(String topicMsgString);

	boolean sendPublishMsg(String publishMsgString);
}
