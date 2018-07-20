package com.omerta.rabbitconsumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.omerta.rabbitconsumer.listener.MyMessageListener;

@Configuration
public class RabbitMQConfig {

	public static final String ROUTING_KEY="com.omerta.samplequeue";
	
	
	@Bean
	Queue queue() {
		return new Queue(ROUTING_KEY, true);
	}
	
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("OmerteQueueExchange");
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
	}
	
	@Bean
	MessageListenerAdapter messageListenerAdapter(MyMessageListener myMessageListener) {
		return new MessageListenerAdapter(myMessageListener, "onMessage");
	}
	
	@Bean
	SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory,MessageListenerAdapter messageListenerAdapter) {
		SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(ROUTING_KEY);
		container.setMessageListener(messageListenerAdapter);
		return container;
	}
	
	
}
