package com.quikr.qsearch.service.impl;

import com.quikr.qsearch.model.Message;
import com.quikr.qsearch.producer.Producer;
import com.quikr.qsearch.repository.MessageRepository;
import com.quikr.qsearch.service.MessageService;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private Producer producer;

    SimpleMessageListenerContainer container;
    @Override
    public String saveText(Message message) {
        String result = "Success";
        try {
            messageRepository.save(message);
            producer.sendMessageInToRabbitMQ(message);
        }catch (Exception e){
            result = "Failure";
        } finally {
            return result;
        }
    }
}
