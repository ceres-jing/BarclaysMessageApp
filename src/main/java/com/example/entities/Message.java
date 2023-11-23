package com.example.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private long id;
    private String content;
    @ManyToOne
    private Person sender;

    public Person getSender() {
        return sender;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message(String content) {
        this.content = content;
    }

    public Message(String content, Person sender) {
        this.content = content;
        this.sender =sender;
    }


    public Message() {


    }

}
