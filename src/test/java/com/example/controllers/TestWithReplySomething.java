package com.example.controllers;
import com.example.entities.Message;
import com.example.services.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest
@WebMvcTest(MessageController.class)
public class TestWithReplySomething {
    @MockBean
    MessageService mockMessageService;
    @Autowired
    MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();


    @Test
    void testForReturnSomeMessage() throws Exception {

        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("m1"));
        messages.add(new Message("m2"));

        String expectedJson = mapper.writeValueAsString(messages);

        when(mockMessageService.findAll()).thenReturn(messages);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages");
        MvcResult result=mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expectedJson))
                .andReturn();
//
        String contentAsString = result.getResponse().getContentAsString();
        Message[] messageArray = mapper.readValue(contentAsString, Message[].class);
        assertEquals(messages.size(),messageArray.length);

        verify(mockMessageService,times(1)).findAll();


    }

    @Test
    void testGetMessageById() throws Exception {
        Long messageId = 1L;
        Message message = new Message("Howdy, I'm message 1");
        when(mockMessageService.getMessageById(messageId)).thenReturn(message);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages/"+Long.toString(messageId));
        MvcResult result=mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockMessageService,times(1)).getMessageById(messageId);
    }


    @Test
    void testGetMessageByBadIndex() throws Exception {
        long messageId =0;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages/"+messageId);
        MvcResult result=mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andReturn();


    }


















}
