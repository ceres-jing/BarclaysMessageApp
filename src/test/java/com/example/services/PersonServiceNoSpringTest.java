package com.example.services;

import com.example.dataaccess.MessageRepository;
import com.example.dataaccess.PersonRepository;
import com.example.entities.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonServiceNoSpringTest {

    PersonRepository mockRepo= mock(PersonRepository.class);

    PersonService personService = new PersonServiceImp(this.mockRepo);

    @BeforeEach
    void beforeEach(){

    }


    @Test
    void testGetPersonByIdOptionalNotEmpty(){


        Person person = new Person("Eliane");

        when(this.mockRepo.findById(any())).thenReturn(Optional.of(person));
        Person actual = personService.getPersonById(100l);
        Assertions.assertEquals(person, actual);

    }

    @Test

    void testPersonByIdOptionalEmpty(){
        long personId= 1L;

        Optional<Person> person =  Optional.empty();
        when(this.mockRepo.findById(personId)).thenReturn(person);
        Person actual = personService.getPersonById(personId);
        Assertions.assertNull(actual);

    }














}
