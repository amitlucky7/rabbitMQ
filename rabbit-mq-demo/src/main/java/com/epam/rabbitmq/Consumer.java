package com.epam.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.newConnection();
		Channel channnel = connection.createChannel();
		
		DeliverCallback deliverCallback = (consumerTag,delivery)->{
			String message = new String(delivery.getBody());
			System.out.println("Message Received= "+message);
		};
	
		channnel.basicConsume("Queue-1",true,deliverCallback, consumerTag ->{});
	}
	
	
}
