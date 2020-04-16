package com.epam.rabbitmqwithspringboot.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfig {

	@Bean
	Exchange exampleExchange(){
		return ExchangeBuilder.directExchange("directExchange")
				.build();
	}
}
