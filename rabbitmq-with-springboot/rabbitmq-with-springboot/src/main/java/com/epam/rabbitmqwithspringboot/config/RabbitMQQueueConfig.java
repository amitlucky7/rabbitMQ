package com.epam.rabbitmqwithspringboot.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfig {

	
	
	@Bean
	Queue exampleQueue2(){
		return  QueueBuilder.durable("header-queue")
				.autoDelete()
				.build();
	}
	
	@Bean
	HeadersExchange myHeaderExchange(){
		return ExchangeBuilder.headersExchange("myHeaderExchange")
				.durable(true)
				.build();
	}

	@Bean
	Binding bindings(){
		Map<String,Object>map = new HashMap<>();
		map.put("item1","mobile");
		map.put("item2","ac");
		return BindingBuilder.bind(exampleQueue2()).to(myHeaderExchange()).whereAny(map).match();
				
	}
	
}
