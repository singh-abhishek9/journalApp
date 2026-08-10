package com.springstarter.journalApp.service;

import com.springstarter.journalApp.repository.userRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class userServiceTest {
    @Autowired
    private userRepository userRepository;

    @Test
    public void testUser(){
        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUserName("Abhishek Singh"));
    }
}
