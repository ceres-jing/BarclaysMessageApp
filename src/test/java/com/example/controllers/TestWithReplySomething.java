package com.example.controllers;

import com.example.entities.Message;
import com.example.services.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.matchers.text.ValuePrinter.print;


@WebMvcTest
public class TestWithReplySomething {
    @MockBean
    MessageService mockMessageService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void testForReturnSomeMessage() throws Exception {

        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("m1"));
        messages.add(new Message("m2"));

        when(mockMessageService.getAllMessages()).thenReturn(messages);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages");
        ResultActions resultActions=mockMvc.perform(requestBuilder);

        MvcResult result= resultActions.andReturn();
        System.out.println(result);
        String contentAsString = result.getRequest().getContentAsString();
        
        ObjectMapper mapper = new ObjectMapper();
        //Message[] messageArray = mapper.readValue(contentAsString, Message[].class);
        //assertEquals(messages.size(),messageArray.length);

        //verify(mockMessageService,times(1)).getAllMessages();

    }









}
