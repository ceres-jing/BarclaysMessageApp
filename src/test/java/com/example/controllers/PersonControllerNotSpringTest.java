package com.example.controllers;

import com.example.entities.Person;
import com.example.services.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PersonControllerNotSpringTest {

    PersonService mockPersonService;
    PersonController personController;

    @BeforeEach
    void beforeEach(){
        mockPersonService = mock(PersonService.class);
        personController= new PersonController(mockPersonService);
    }

    @Test
    void testGetPersonById(){

        long personId = 1L;
        when(mockPersonService.getPersonById(personId)).thenReturn(new Person("It doesnt matter what kind of name"));
        this.personController.getPersonById(personId);
        verify(mockPersonService,times(1)).getPersonById(personId);

    }

    @Test
    void testBadPersonRequest(){
        when(mockPersonService.getPersonById(0L)).thenReturn(null);
        assertThrows(ResponseStatusException.class, ()->{
            this.personController.getPersonById(0L);
        });

    }







}
