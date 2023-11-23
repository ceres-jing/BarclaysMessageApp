package com.example.controllers;

import com.example.entities.Message;
import com.example.entities.Person;
import com.example.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
class MessageControllerTest {
    @MockBean
    MessageService mockMessageService;
    @Autowired
    MockMvc mockMvc;
    @Test
    void getAllMessages() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages"); //build a fake https requests
        mockMvc.perform(requestBuilder); // send a request
        verify(mockMessageService,times(1)).findAll();
    }

    @Test
    void getMessagesByUserEmail() throws Exception {

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("hello rui"));
        messages.add(new Message("hello dave"));

        String email= "bill@iscooler.com";

        when(mockMessageService.getMessageBySenderEmail(email)).thenReturn(messages);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages/sender/email/"+email);
        MvcResult result=mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockMessageService,times(1)).getMessageBySenderEmail(email);


    }

    @Test
    void getMessagesByWrongUserEmail() throws Exception {

        List<Message> messages = new ArrayList<>();
        Person rui = new Person("rui","rui.jing@barclays.com");
        messages.add(new Message("hello rui", rui));
        Person dave = new Person("dave","dave@dave.com");
        messages.add(new Message("hello dave",dave));

        String email= "rui.jing@barclssš.com";

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages/sender/email/"+email);
        MvcResult result=mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andReturn();


    }




}