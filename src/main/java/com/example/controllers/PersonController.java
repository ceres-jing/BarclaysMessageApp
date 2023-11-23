package com.example.controllers;

import com.example.entities.Message;
import com.example.entities.Person;
import com.example.services.MessageService;
import com.example.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons") //This annotation is used to map HTTP GET requests to the specified URI ("/message") to the getMessage method.
    public  Iterable<Person> getAllPersons(){
        return personService.findAll();
    }


    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@RequestBody Person person){
        Person newPerson;

        try {
            newPerson = this.personService.addPerson(person);
        }catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return newPerson;
    }



    @GetMapping("/persons/{personId}")
    public Person getPersonById(@PathVariable Long personId){

        Person person = personService.getPersonById(personId);
        if (person == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return person;

    }







}
