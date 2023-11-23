package com.example.services;

import com.example.TestUtilities;
import com.example.dataaccess.PersonRepository;
import com.example.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceSomeSpringTest {

    @MockBean
    PersonRepository repo;

    @Autowired
    PersonService personService;


    @Test
    void findAllWithSpringDi(){
        List<Person> persons = TestUtilities.getPersonList();
        when(this.repo.findAll()).thenReturn(persons);
        List<Person> actualPerson = personService.findAll();
        assertEquals(persons, actualPerson);

    }




}