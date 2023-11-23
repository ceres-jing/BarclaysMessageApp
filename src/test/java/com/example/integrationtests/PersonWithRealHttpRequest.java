package com.example.integrationtests;

import com.example.entities.Message;
import com.example.entities.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class PersonWithRealHttpRequest {
    ObjectMapper mapper = new ObjectMapper();

    @Disabled("Must be run while application is running.")
    @Test
    public void testGettingAllPeopleInfo() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/persons");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        Person[] actualPerson = mapper.readValue(response.getEntity().getContent(), Person[].class);
        assertEquals("rui",actualPerson[0].getFirstName());
        assertEquals("dave",actualPerson[1].getFirstName());

    }
}
