package com.springstarter.journalApp.controller;

import com.springstarter.journalApp.entity.User;
import com.springstarter.journalApp.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicController {
    @Autowired
    private userService userService;

    @GetMapping("/health-check")
    public String healthCheck()
    {
        return "ok";
    }

    //post mapping
    @PostMapping("/create-user")
    public void saveUser(@RequestBody User user) {
        userService.saveNewEntry(user);
    }

}
