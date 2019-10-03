package com.example.demo.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.DirectExchangeRoutingKeyConfigurer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DirectConfig {

    @Bean
    public Queue helloworldQueue() {
        return new Queue("hello");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("direct", true, true);
    }

    @Bean
    DirectExchangeRoutingKeyConfigurer bandingExchageMsg(Queue helloworldQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(helloworldQueue).to(directExchange);
    }

}
