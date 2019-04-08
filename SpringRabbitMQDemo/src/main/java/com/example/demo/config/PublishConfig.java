package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublishConfig {
	
	@Bean
	public Queue publishQueueA(){
		return new Queue("publishQueueA",true, false, true);
	}
	
	@Bean
	public Queue publishQueueB(){
		return new Queue("publishQueueB",true, false, true);
	}
	
	@Bean
	public Queue publishQueueC(){
		return new Queue("publishQueueC",true, false, true);
	}
	
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("publishExchange", true, false);
	}
	
	@Bean
	public Binding exchangeQueueBind1(Queue publishQueueA, FanoutExchange fanoutExchange){
		return BindingBuilder.bind(publishQueueA).to(fanoutExchange);
	}
	
	@Bean
	public Binding exchangeQueueBind2(Queue publishQueueB, FanoutExchange fanoutExchange){
		return BindingBuilder.bind(publishQueueB).to(fanoutExchange);
	}
	
	@Bean
	public Binding exchangeQueueBind3(Queue publishQueueC, FanoutExchange fanoutExchange){
		return BindingBuilder.bind(publishQueueC).to(fanoutExchange);
	}
}
