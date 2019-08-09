package com.quikr.qsearch.controller;


import com.quikr.qsearch.model.Message;
import com.quikr.qsearch.model.MessageES;
import com.quikr.qsearch.producer.Producer;
import com.quikr.qsearch.repository.MessageESRepository;
import com.quikr.qsearch.repository.MessageRepository;
import com.quikr.qsearch.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageESRepository messageESRepository;
    @Autowired
    private MessageService messageService;
    @Autowired
    private Producer producer;


    @RequestMapping(method = RequestMethod.POST, value = "/saveMessage")
    public void saveMessage(@RequestBody Message message){
        // send message into MYSQL
        messageService.saveText(message);
        // save data into RabbitMQ
        producer.sendMessageInToRabbitMQ(message);
    }

    @RequestMapping("/findByText/{text}")
    public List<MessageES> findByText(@PathVariable("text")String text){
        System.out.println("Enter text for search:" + text);
        return messageESRepository.findByText(text);
    }
}
