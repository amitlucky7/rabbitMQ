package com.epam.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectPublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		String message = "This is TV";
		channel.basicPublish("Direct_Exchange", "mobile",null, message.getBytes());
		channel.close();
		connection.close();
		System.out.println("Message successully published.....");
	}
}
