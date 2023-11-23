package com.example.controllers;

import com.example.entities.Message;
import com.example.services.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MessageControllerNoSpringTest {

    MessageService mockMessageService;
    MessageController messageController;

    @BeforeEach
    void beforeEach() {
        mockMessageService = mock(MessageService.class);
        messageController = new MessageController(mockMessageService);
    }


    @Test
    void getAllMessages() {
        this.messageController.getMessage();
        verify(mockMessageService, times(1)).findAll();
    }

    @Test
    void testGetMessageById() {
        Long messageId = 1L;
        when(mockMessageService.getMessageById(messageId)).thenReturn(new Message("hello world"));
        this.messageController.getMessageById(messageId);
        verify(mockMessageService, times(1)).getMessageById(messageId);
    }

    @Test
    void testGetMessageBySenderEmail(){
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("hello rui"));
        messages.add(new Message("hello dave"));

        String email= "bill@iscooler.com";
        when(mockMessageService.getMessageBySenderEmail(email)).thenReturn(messages);
        this.messageController.getMessageBySenderEmail(email);
        verify(mockMessageService,times(1)).getMessageBySenderEmail(email);

    }




    @Test
    void testBadRequest() {

        when(mockMessageService.getMessageById(0L)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> {
            this.messageController.getMessageById(0L);
        });

    }

}