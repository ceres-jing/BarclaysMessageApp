package com.example.services;

import com.example.dataaccess.MessageRepository;
import com.example.dataaccess.PersonRepository;
import com.example.entities.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class PersonServiceImp implements PersonService{

    public PersonRepository personRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;}

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();

    };

    @Override
    public Person getPersonById(long personId) {

        Optional<Person> person = personRepository.findById(personId);
        return person.orElse(null);


    }
    @Override
    public Person addPerson(Person person) {
        if(person.getId() != null && person.getId() != 0)
            throw  new IllegalArgumentException("The id provided for a create/post must be null or zero.");

        return this.personRepository.save(person);
    }

}
