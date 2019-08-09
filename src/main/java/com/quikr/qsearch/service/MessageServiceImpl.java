package com.quikr.qsearch.service;

import com.quikr.qsearch.model.Message;
import com.quikr.qsearch.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    private List<Message> messages = new ArrayList<>(Arrays.asList(
            new Message((long)1, "hello"), new Message((long)2, "hello world"), new Message((long)3, "welcome"), new Message((long)4, "welcome hello")
    ));

    @Override
    public void saveText(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> getTextByWord(String word) {
//        return messageRepository.findByWord(word);
        return null;
    }
}
