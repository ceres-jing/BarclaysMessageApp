package com.example.services;

import com.example.TestUtilities;
import com.example.dataaccess.MessageRepository;
import com.example.entities.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class MessageServiceSomeSpringTest {

    @Autowired
    MessageService messageService;

    @MockBean
    MessageRepository mockRepo;
    @Test
    void findAllWithSpringDi(){

        List<Message> messages = TestUtilities.getMessageList();
        //MessageRepository mockRepo = mock(MessageRepository.class);
        when(this.mockRepo.findAll()).thenReturn(messages);
        List<Message> actualMessage=messageService.findAll();

        Assertions.assertEquals(messages,actualMessage);

    }

    @Test
    void testRepoCalled(){
        List<Message> actualMessage=messageService.findAll();

        verify(mockRepo,times(1)).findAll();

    }

}