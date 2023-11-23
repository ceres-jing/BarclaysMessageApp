package com.example.controllers;
import com.example.entities.Message;
import com.example.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/messages")
public class MessageController {

    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("") //This annotation is used to map HTTP GET requests to the specified URI ("/message") to the getMessage method.
    public Iterable<Message> getMessage(){
        return messageService.findAll();
    }

    @GetMapping("/{messageId}")
    public Message getMessageById(@PathVariable long messageId){
        Message message = messageService.getMessageById(messageId);
        if (message == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");

        return message;
    }

    @GetMapping("/sender/email/{email}")
    public List<Message> getMessageBySenderEmail(@PathVariable String email){
        List<Message> messages=  messageService.getMessageBySenderEmail(email);

        if (messages.isEmpty() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Messages not found");

        return messages;
    }
    @GetMapping("/name/nameId/{nameId}")
    public List<Message> getMessageByNameId(@PathVariable Long nameId){
        return messageService.getMessageByNameId(nameId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message addMessage(@RequestBody Message message){

        Message newMessage;

        try{newMessage=this.messageService.addMessage(message);

        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }

        return newMessage;
    }







}
