package ru.gb.client.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientIntegrationTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetImage() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://127.0.0.1:8080/", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
