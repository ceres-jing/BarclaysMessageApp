package com.example.services;

import com.example.entities.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageService {

    public List<Message> getAllMessages(){

        return new ArrayList<>();  //TODO:

    }
}
