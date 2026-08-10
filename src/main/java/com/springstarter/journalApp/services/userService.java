package com.springstarter.journalApp.services;

import com.springstarter.journalApp.entity.User;
import com.springstarter.journalApp.repository.userRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class userService {

        @Autowired
        private userRepository userRepository;
        private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
         public void saveEntry(User newuser)
         {
            userRepository.save(newuser);
         }
        public boolean saveNewEntry(User newUser)
        {
            try {
                newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                newUser.setRoles(Arrays.asList("USER"));
                userRepository.save(newUser);
                return true;
            } catch (Exception e) {
               log.error("error occured");
               log.info("info occured");
               log.trace("trace");
               log.debug("debug");
               log.warn("warn");
               return false;
            }
        }
        public void saveAdminDetails(User newUser)
        {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            newUser.setRoles(Arrays.asList("USER","ADMIN"));
            userRepository.save(newUser);
        }
         public Optional<User> findById(ObjectId id)
         {
             return userRepository.findById(id);
         }
         public List<User> getAll()
         {
             return userRepository.findAll();
         }
         public void deleteById(ObjectId id)
         {
             userRepository.deleteById(id);
         }
         public User findByUserName(String userName)
         {
            return userRepository.findByUserName(userName);
         }
}
