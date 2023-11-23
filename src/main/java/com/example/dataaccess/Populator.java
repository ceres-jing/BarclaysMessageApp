package com.example.dataaccess;

import com.example.entities.Message;
import com.example.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Component
//public class Populator {
//
//    MessageRepository repo;
//    PersonRepository personRepository;
//
//    @Autowired
//    public Populator(MessageRepository repo) {
//        this.repo = repo;
//    }
//
//    @EventListener(ContextRefreshedEvent.class)
//    public void populate(){
//        Person rui = new Person("Rui","rui.jing@barclays.com");
//        rui=this.personRepository.save(rui);
//        Message m1 = new Message ("message1");
//        this.repo.save(m1);
//        Message m2 = new Message ("message2");
//        this.repo.save(m2);
//
//
//
//    }
//}


@Component
public class Populator {

    MessageRepository messageRepository;
    PersonRepository personRepository;

    @Autowired
    public Populator(MessageRepository messageRepository, PersonRepository personRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
    }

    //@EventListener(ContextRefreshedEvent.class)
    public void populate() {
        Person rui = new Person("rui", "rui.jing@barclays.com");
        rui = personRepository.save(rui);

        Person dave = new Person("dave", "dave@dave.com");
        dave = personRepository.save(dave);

        Message m1 = new Message("rui's message", rui);
        this.messageRepository.save(m1);

        Message m2 = new Message("dave's message", dave);
        this.messageRepository.save(m2);
    }
}
