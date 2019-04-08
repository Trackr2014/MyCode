package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
	
	@Bean
	public Queue topicQueue1() {
		return new Queue("rabbitmq.topic1");
	}
	
	@Bean
	public Queue topicQueue2() {
		return new Queue("rabbitmq.topic2");
	}
	
	@Bean
	public TopicExchange topicExchangeA() {
		return new TopicExchange("exchangeA");
	}

	@Bean
	public Binding bindingProcFir(TopicExchange topicExchangeA, Queue topicQueue1) {
		return BindingBuilder.bind(topicQueue1).to(topicExchangeA).with("log.#");
	}
	
	@Bean
	public Binding bindingProcTwo(TopicExchange topicExchangeA, Queue topicQueue2) {
		return BindingBuilder.bind(topicQueue2).to(topicExchangeA).with("log.debug.*");
	}
	
	@Bean
	public Binding bindingProcThree(TopicExchange topicExchangeA, Queue topicQueue2) {
		return BindingBuilder.bind(topicQueue2).to(topicExchangeA).with("log.debug");
	}

}
