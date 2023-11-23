package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;

    private String email;

    public String getEmail() {
        return email;
    }

//    @OneToMany (mappedBy = "sender") //the relation between sender and message
//    private List<Message> sentMessages =  new ArrayList<>();;

//    public List<Message> getSentMessages() {
//        return sentMessages;
//    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }


    public Person(String firstName,String email) {
        this.firstName = firstName;
        this.email= email;

    }

    public Person(String firstName) {
        this.firstName = firstName;
    }



    public Person() {

    }
}
