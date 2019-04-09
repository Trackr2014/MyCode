package com.example.demo.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitMQTemplate implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{
	
	@Autowired
	ConnectionFactory connectionFactory;
	
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        return rabbitTemplate;
    }

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			System.out.println("消息发送到Exchange成功！");
		} else {
			System.out.println("消息发送到Exchange失败！");
		}
	}

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode
				+ ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
	}
}
