package com.quikr.qsearch.consumer;

import com.quikr.qsearch.QsearchApplication;
import com.quikr.qsearch.model.Message;
import com.quikr.qsearch.model.MessageES;
import com.quikr.qsearch.service.MessageESService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    MessageESService messageESService;
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    @Autowired
    MessageListenerAdapter listenerAdapter;

    @RabbitListener(queues = QsearchApplication.queueName)
    public void receiveMessage(Message message){
        log.info("Received Message from RabbitMQ : " + message.toString() + " " +  listenerAdapter);
        saveMessageInoElasticSearch(message);
    }

    private String saveMessageInoElasticSearch(Message message) {
        MessageES messageES = new MessageES(message.getId(), message.getText());
        return messageESService.save(messageES);
    }


}
