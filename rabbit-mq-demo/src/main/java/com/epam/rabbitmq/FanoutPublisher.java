package com.epam.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutPublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		String message = "Message for Mobile and AC";
		channel.basicPublish("Fan_Exchange","",null, message.getBytes());
		channel.close();
		connection.close();
		System.out.println("Message successully published.....");
	}
}
