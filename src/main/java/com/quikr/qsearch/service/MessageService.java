package com.quikr.qsearch.service;

import com.quikr.qsearch.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MessageService {
    public void saveText(Message message);
    public List<Message> getTextByWord(String word);
}
