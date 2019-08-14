package com.quikr.qsearch.controller;

import com.quikr.qsearch.model.Message;
import com.quikr.qsearch.model.MessageES;
import com.quikr.qsearch.service.MessageESService;
import com.quikr.qsearch.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageESService messageESService;

    @RequestMapping(method = RequestMethod.POST, value = "/saveMessage")
    public String saveMessage(@RequestBody Message message) {
        return messageService.saveText(message);
    }

    @RequestMapping("/findByText/{text}")
    public List<MessageES> findByText(@PathVariable("text") String text) {
        log.info("Entered text for search:" + text);
        return messageESService.getTextByWord(text);
    }
}
