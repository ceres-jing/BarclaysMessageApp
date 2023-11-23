package com.example.integrationtests;



import com.example.dataaccess.PersonRepository;
import com.example.entities.Message;
import com.example.entities.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddMessageWithMockHttpRequestIT {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PersonRepository personRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    Person existingPerson;
    Person newPerson;

    @BeforeEach
    void beforeEach() {
        if(existingPerson == null)
            existingPerson = new Person("Existing Person", "iexist@universe.com");
        if(existingPerson.getId() == null)
            this.personRepository.save(existingPerson);
        // Will not have an ID
        newPerson = new Person("New Person", "noob@noob.com");
    }

    static final String NEW_MESSAGE_CONTENT = "This is the content of a new message";

    @Test
    void testAddMessageHappyPath() throws Exception {

        //sending a new message with post method
        Message newMessage = new Message(NEW_MESSAGE_CONTENT, this.existingPerson);
        //mapper into into json format.
        String json = this.objectMapper.writeValueAsString(newMessage);

        MvcResult result =
                this.mockMvc.perform(MockMvcRequestBuilders.post("/messages")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))   // mock the https requests with the json created before.
                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        checkMessageFields(result, newMessage);

    }

    private void checkMessageFields(MvcResult result, Message expectedMessage) throws UnsupportedEncodingException, JsonProcessingException {
        String resultJson = result.getResponse().getContentAsString();
        Message resultMessage = this.objectMapper.readValue(resultJson, Message.class);

        assertEquals(expectedMessage.getSender().getFirstName(), resultMessage.getSender().getFirstName());
        assertEquals(expectedMessage.getSender().getEmail(), resultMessage.getSender().getEmail());
        assertEquals(expectedMessage.getContent(), resultMessage.getContent());

        Assertions.assertNotNull(resultMessage.getId());
        Assertions.assertTrue(resultMessage.getId() > 0);

        Assertions.assertNotNull(resultMessage.getSender().getId());
        Assertions.assertTrue(resultMessage.getSender().getId() > 0);
    }



}
