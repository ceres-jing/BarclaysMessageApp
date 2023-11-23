package com.example.controllers;


import com.example.entities.Message;
import com.example.entities.Person;
import com.example.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class TestWithReplyPerson {

    @MockBean
    PersonService mockPersonService;

    @Autowired
    MockMvc mockMvc;

    @Test

    void testGetPersonById() throws Exception {
        Long personId = 2L;
        when(mockPersonService.getPersonById(personId)).thenReturn(new Person("It doesnt matter what kind of name"));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons/" + Long.toString(personId));
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockPersonService, times(1)).getPersonById(personId);
    }


        @Test
        void testingPersonWithBadIndex() throws Exception {
        Long personId = 0L;
        when(mockPersonService.getPersonById(personId)).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons/" + Long.toString(personId));
            MvcResult result = mockMvc.perform(requestBuilder)
                    .andExpect(status().isNotFound())
                    .andReturn();



    }









}
