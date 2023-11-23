package com.example.controllers;

import com.example.entities.Message;
import com.example.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class MessageControllerSomeSpringTest {
    @Autowired
    MessageController uut;
    @MockBean
    MessageService mockMessageService;
    @Test
    public void getObjectsFromContextAndTestBehavior(){
        List<Message> messages = uut.getAllMessages();
        verify(mockMessageService, times(1)).getAllMessages();




    }
}
