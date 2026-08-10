package com.springstarter.journalApp.services;

import com.springstarter.journalApp.entity.User;
import com.springstarter.journalApp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(userName);

        //if user found then
        if(user!=null)
        {
           return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        //if user not found
        throw new UsernameNotFoundException("user not found with this username"+ userName);

    }
}
