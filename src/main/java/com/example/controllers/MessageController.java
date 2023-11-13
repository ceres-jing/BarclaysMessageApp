package com.example.controllers;
import com.example.entities.Message;
import com.example.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class MessageController {

    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages") //This annotation is used to map HTTP GET requests to the specified URI ("/message") to the getMessage method.

    public List<Message> getMessage(){
        return messageService.getAllMessages();

    }




}
