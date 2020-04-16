package com.epam.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class DirectConsumer {
	public static void main(String[] args) throws IOException, TimeoutException, ParseException {
		ConnectionFactory connectionFactory= new ConnectionFactory();
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		DeliverCallback deliverCallback = (consumerTag,delivery)->{
			System.out.println("Message is="+new String(delivery.getBody()));
		};
		channel.basicConsume("TV",true,deliverCallback, consumerTag ->{});
	}

}
