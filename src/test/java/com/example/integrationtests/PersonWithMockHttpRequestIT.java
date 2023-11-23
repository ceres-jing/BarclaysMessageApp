package com.example.integrationtests;


import com.example.entities.Message;
import com.example.entities.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:test-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //what does this do?
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class PersonWithMockHttpRequestIT {

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGettingAllPeople() throws Exception {

        final String expectedJson = """
                [{"id":1,"firstName":"p1"},{"id":2,"firstName":"p2"},{"id":3,"firstName":"p3"},{"id":4,"firstName":"p4"}]""";

        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/persons")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        //.andExpect(content().json(expectedJson)) //?? what does this do ?
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();

        Person[] actualPerson = mapper.readValue(contentAsJson,Person[].class);

        assertEquals("p3",actualPerson[2].getFirstName());
        assertEquals("p4",actualPerson[3].getFirstName());

    }

    @Test
    public void testFindMessagesBySenderEmail() throws Exception {

        String senderEmail = "bill@iscooler.com";

        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/messages/sender/email/"+senderEmail)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Message[] actualMessages = mapper.readValue(contentAsJson,Message[].class);

        assertEquals(2,actualMessages.length);
    }


}
