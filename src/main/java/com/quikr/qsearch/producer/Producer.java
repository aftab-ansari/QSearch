package com.quikr.qsearch.producer;

import com.quikr.qsearch.QsearchApplication;
import com.quikr.qsearch.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageInToRabbitMQ(Message message) {
        log.info("Sending message to the queue using routingKey {}. Message= {}", QsearchApplication.routingKey, message.toString());
        rabbitTemplate.convertAndSend(QsearchApplication.exchangeName, QsearchApplication.routingKey, message);
        log.info("Message has been sent to the queue");

    }
}
