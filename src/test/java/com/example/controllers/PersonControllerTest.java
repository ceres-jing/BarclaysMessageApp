package com.example.controllers;

import com.example.services.MessageService;
import com.example.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PersonControllerTest {

    @Autowired
    PersonController uut;

    @MockBean
    PersonService personService;

    @Test
    public void testGetAllPeople(){
        uut.getAllPersons();
        verify(personService,times(1)).findAll();
    }



}