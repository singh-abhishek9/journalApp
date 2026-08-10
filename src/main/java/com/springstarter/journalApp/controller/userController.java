package com.springstarter.journalApp.controller;

import com.springstarter.journalApp.api.response.weatherResponse;
import com.springstarter.journalApp.entity.User;
import com.springstarter.journalApp.repository.userRepository;
import com.springstarter.journalApp.services.userService;
import com.springstarter.journalApp.services.weatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService userService;
    @Autowired
    private userRepository userRepository;

    @Autowired
    private weatherService weatherService;
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User newUser)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User userInDb=userService.findByUserName(userName);
        userInDb.setUserName(newUser.getUserName());
        userInDb.setPassword(newUser.getPassword());
        userService.saveNewEntry(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  @GetMapping
   public ResponseEntity<?> greeting(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
      weatherResponse weatherResponse=weatherService.getWeather("Mumbai");
      String greeting="";
      if(weatherResponse!=null){
          greeting =",weather feels like"+weatherResponse.getCurrent().getFeelslike();
      }
        return new ResponseEntity<>("Hi" + authentication.getName() +" "+greeting,HttpStatus.OK);
   }

}
