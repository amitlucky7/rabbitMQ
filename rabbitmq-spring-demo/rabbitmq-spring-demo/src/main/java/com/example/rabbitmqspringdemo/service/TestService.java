package com.example.rabbitmqspringdemo.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.rabbitmqspringdemo.Controller.Person;



@Service
public class TestService {

	@RabbitListener(queues ="AC")
	public void getData(byte [] message) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = new ByteArrayInputStream(message);
		ObjectInput input = new ObjectInputStream(bis);
		Person person = (Person) input.readObject();
		System.out.println("name object="+person.getName());
	}
}
