package com.quikr.qsearch;

import com.quikr.qsearch.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QsearchApplication {

	private static final Logger log = LoggerFactory.getLogger(QsearchApplication.class);

	public static final String exchangeName = "qsearch.rabbitmq.topicexchange";
	public static final String queueName = "qsearch.rabbitmq.queue";
	public static final String routingKey = "qsearch.rabbitmq.routingkey";

	@Bean
	Queue queue() {
		log.info("queue is creating");
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		log.info("Topic exchange is creating");
		return new TopicExchange(exchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		log.info("exchange is binding with queue");
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		container.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Consumer consumer) {
		return new MessageListenerAdapter(consumer, "receiveMessage");
	}


	public static void main(String[] args) {
		SpringApplication.run(QsearchApplication.class, args);
	}

}
