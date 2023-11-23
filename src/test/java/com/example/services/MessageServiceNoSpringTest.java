package com.example.services;

import com.example.TestUtilities;
import com.example.dataaccess.MessageRepository;
import com.example.entities.Message;
import com.example.entities.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageServiceNoSpringTest {
    MessageRepository mockRepo = mock(MessageRepository.class);
    MessageService messageService = new MessageService(this.mockRepo);

    @BeforeEach
    void beforeEach(){
        this.mockRepo = mock(MessageRepository.class);
        this.messageService = new MessageService(this.mockRepo);
    }



    @Test
    void finnAll(){
        List<Message> messages = TestUtilities.getMessageList();
        MessageRepository mockRepo = mock(MessageRepository.class);
        when(this.mockRepo.findAll()).thenReturn(messages);
        List<Message> actualMessage=messageService.findAll();
        Assertions.assertEquals(messages,actualMessage);
    }

    @Test
    void testGetMessageByIdOptionalEmpty(){

        long messageId = 1;
        Optional<Message> optionalMessage = Optional.empty();
        when(this.mockRepo.findById(messageId)).thenReturn(optionalMessage);
        Message actualMessage = messageService.getMessageById(messageId);
        Assertions.assertNull(actualMessage);
    }
    @Test

    void testGetMessageByIdOptionalNotEmpty() {

        Message message = new Message("Hello world, i am an AI ");

        when(this.mockRepo.findById(any())).thenReturn(Optional.of(message));

        Message actual = messageService.getMessageById(100l);  // repo been controlled, so no matter what id you put,
        // it only return  that message you add on mock repo
        Assertions.assertEquals(message, actual);
    }

    @Test
    void testGetMessageByEmailNotEmpty(){

        Person rui = new Person("rui","rui.jing@barclays.com");

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("hello rui"));
        when(this.mockRepo.findMessagesBySenderEmail(any())).thenReturn(messages);
        List<Message> actual = messageService.getMessageBySenderEmail("rui.jing@barclays.com");
        Assertions.assertEquals(messages, actual);

    }

    @Test

    void testGetMessageByEmailOptionalEmpty() {

        ArrayList emptyList = new ArrayList<>();
        when(this.mockRepo.findMessagesBySenderEmail(any())).thenReturn(emptyList);
        List<Message> actual = messageService.getMessageBySenderEmail("rui.jing@barclays.com");
        Assertions.assertNull(actual);

    }



























}