package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfig {
	
	@Bean
	public Queue headerQueue() {
		return new Queue("headersQueue");
	}
	
	@Bean
	HeadersExchange headersExchange() {
		return new HeadersExchange("headersExchange", true, true);
	}
	
	@Bean
	Binding bindingQueue2Exchange(Queue headerQueue, HeadersExchange headersExchange) {
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("info", "info");
		header.put("debug", "debug");
		header.put("error", "error");
		return BindingBuilder.bind(headerQueue).to(headersExchange).whereAny(header).match();
	}
}
