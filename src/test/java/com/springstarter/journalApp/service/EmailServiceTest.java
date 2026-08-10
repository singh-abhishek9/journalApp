package com.springstarter.journalApp.service;

import com.springstarter.journalApp.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService EmailService;

    @Test
    public void sendEmail(){
        EmailService.sendEmail(
                "abhisheksingh.aimt@gmail.com",
                "Testing Springboot app",
                "I hope you are doing well");
    }
}
