package com.example.services;

import com.example.dataaccess.MessageRepository;
import com.example.entities.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;



public interface PersonService {
    public List<Person> findAll();

    Person getPersonById(long personId);


    Person addPerson(Person person);
}
