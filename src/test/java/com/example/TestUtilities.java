package com.example;

import com.example.entities.Message;
import com.example.entities.Person;

import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class TestUtilities {
    public static ArrayList<Message> getMessageList() {
        ArrayList<Message> messages = new ArrayList<>();
        messages.add( new Message("message1"));
        messages.add( new Message("message2"));
        return messages;
    }

    public static ArrayList<Person> getPersonList() {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add( new Person("name1"));
        persons.add( new Person("name2"));
        return persons;
    }


}
