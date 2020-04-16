package com.epam.rabbitmqwithspringboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.rabbitmqwithspringboot.listener.RabbitMQMessageListener;

@Configuration
public class RabbitMQConfig {
	
	
	@Value("${application.port}")
	private String portNumber;
	
	@Value("${rabbitmq.username}")
	private String userName;
	
	@Value("${rabbitmq.password}")
	private String password;
	
	@Value("${rabbitmq.queuename}")
	private String queueName;
	
	@Bean
	Queue myQueue(){
		return new Queue(queueName,true);
	}
	
	@Bean
	ConnectionFactory connectionFactory(){
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(portNumber);
		cachingConnectionFactory.setUsername(userName);
		cachingConnectionFactory.setPassword(password);
		return cachingConnectionFactory;
	}
	
	@Bean
	MessageListenerContainer messageListenerContainer(){
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory());
		simpleMessageListenerContainer.setQueues(myQueue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
		return simpleMessageListenerContainer;
	}
}
