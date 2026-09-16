package com.springstarter.journalApp.controller;
import com.springstarter.journalApp.entity.User;
import com.springstarter.journalApp.services.AppCache;
import com.springstarter.journalApp.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private userService userService;

    @Autowired
    private AppCache AppCache;

    @GetMapping("/get-user")
    public ResponseEntity<?> getAllDetails(){

        List<User> all=userService.getAll();
        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addAdminDetails(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userService.saveAdminDetails(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/cache-start")
    public void startAppCache(){
        AppCache.init();
    }
}
