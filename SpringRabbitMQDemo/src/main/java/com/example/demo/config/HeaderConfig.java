package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wang.pengfei
 * 本实例采用头交换机,设置QueueA 和  QueueB,采用不同的匹配策略.
 */
@Configuration
public class HeaderConfig {

    @Bean
    public Queue headerQueueA() {
        return new Queue("headersQueueA");
    }

    @Bean
    public Queue headerQueueB() {
        return new Queue("headersQueueB");
    }

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange("headersExchange", true, true);
    }

    @Bean
    Binding bindingQueue1Exchange(Queue headerQueueA, HeadersExchange headersExchange) {
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("info", "info");
        header.put("debug", "debug");
        return BindingBuilder.bind(headerQueueA).to(headersExchange).whereAll(header).match();
    }

    @Bean
    Binding bindingQueue2Exchange(Queue headerQueueB, HeadersExchange headersExchange) {
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("error", "error");
        return BindingBuilder.bind(headerQueueB).to(headersExchange).where("error").exists();
    }
}
