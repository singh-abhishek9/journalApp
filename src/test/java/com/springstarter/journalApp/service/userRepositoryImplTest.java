package com.springstarter.journalApp.service;


import com.springstarter.journalApp.repository.userRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userRepositoryImplTest {

    @Autowired
    private userRepositoryImpl userRepositoryImpl;

    @Test
    public void testSaveUser(){
        userRepositoryImpl.getUser();
    }
}
