package com.epam.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicExchangePublisher {

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		String message = "Message for Mobile and TV";
		Map<String,Object> map =new HashMap<>();
		map.put("item1", "mobile");
		map.put("item2","television");
		BasicProperties props = new BasicProperties();
		props = props.builder().headers(map).build();
		channel.basicPublish("Headers-Exchange", "",props , message.getBytes());
		
		/*#.ac	
		*.mobile.*	
		*.tv.*/
		System.out.println("Message published successfully");
	}

}
