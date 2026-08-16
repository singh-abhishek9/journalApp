package com.springstarter.journalApp.controller;

import com.springstarter.journalApp.entity.User;
import com.springstarter.journalApp.services.UserDetailsServiceImp;
import com.springstarter.journalApp.services.userService;
import com.springstarter.journalApp.utilities.jwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class publicController {
    @Autowired
    private userService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private jwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck()
    {
        return "ok";
    }

    //post mapping
    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.saveNewEntry(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
            UserDetails userDetails=userDetailsServiceImp.loadUserByUsername(user.getUserName());
            String jwt= jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Authentication failed ",e);
            return new ResponseEntity<>("InCorrect UserName or password",HttpStatus.BAD_REQUEST);

        }
    }

}
