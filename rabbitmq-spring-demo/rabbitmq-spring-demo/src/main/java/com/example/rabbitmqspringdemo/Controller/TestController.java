package com.example.rabbitmqspringdemo.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/*@GetMapping("/test1/{name}")
	public String testAPI(@PathVariable("name") String name){
		Person person = new Person(1L,name);
		//rabbitTemplate.convertAndSend("Mobile", person);
		//rabbitTemplate.convertAndSend("Direct_Exchange", "mobile", person);
		//rabbitTemplate.convertAndSend("Fanout-Exchange","",person);
		 rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", person);
		return "success";
	}*/
	
	@GetMapping("/test2/{name}")
	public String testAPI(@PathVariable("name") String name) throws IOException{
		Person person = new Person(1L,name);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(person);
		out.flush();
		out.close();

		byte [] byteArr = bos.toByteArray();
		bos.close();
		Message message = MessageBuilder.withBody(byteArr)
						.setHeader("item1", "tv").
						setHeader("item2", "television").build();
				
		rabbitTemplate.send("Headers-Exchange", "", message);
		return "success";
	}
}
