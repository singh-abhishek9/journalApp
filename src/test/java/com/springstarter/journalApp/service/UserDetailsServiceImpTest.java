package com.springstarter.journalApp.service;

import com.springstarter.journalApp.entity.User;
import com.springstarter.journalApp.repository.userRepository;
import com.springstarter.journalApp.services.UserDetailsServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.core.userdetails.UserDetails;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImpTest {

    @InjectMocks
    private UserDetailsServiceImp userDetailsService;

    @Mock
    private userRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadByUserNameTest()
    {
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(
                        User.builder()
                                .userName("abhishek")
                                .password("abhishek")
                                .roles((new ArrayList<>()))
                                .build());

        UserDetails user= userDetailsService.loadUserByUsername("Abhishek Singh");
        Assertions.assertNotNull(user);
    }
}
