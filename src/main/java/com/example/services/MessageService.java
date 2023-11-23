package com.example.services;

import com.example.dataaccess.MessageRepository;
import com.example.dataaccess.PersonRepository;
import com.example.entities.Message;
import com.example.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class MessageService {
    MessageRepository messageRepository;
    PersonRepository personRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, PersonRepository personRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
    }

    //@Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll(){
        return this.messageRepository.findAll();
    }
    public Message getMessageById(long messageId) {
        Optional<Message> message = this.messageRepository.findById(messageId);
        return message.orElse(null);

    }

    public List<Message> getMessageBySenderEmail(String email) {
        List<Message> messages = this.messageRepository.findMessagesBySenderEmail(email);
        if (messages.isEmpty())
            return null;
        else return messages;
    }

    public static final String SENDER_MUST_EXIST = "The sender of the message must already exist.";

    public Message addMessage( Message message ){
        if ( message.getId() != 0 && message.getId() != null )
            throw new IllegalArgumentException("The message id provided for a create/post must be null or zero.");

        Person sender = message.getSender();

        if(sender.getId()==null){
            throw new IllegalArgumentException("HELLO WORLD 1");
        }

        if(!this.personRepository.existsById(sender.getId())){
            throw new IllegalArgumentException("HELLO WORLD 2");
        }

        return this.messageRepository.save(message);

    };

    public List<Message> getMessageByNameId(Long nameId) {
        return messageRepository.findMessagesById(nameId);
    }
}
