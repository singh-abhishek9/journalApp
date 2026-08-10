package com.springstarter.journalApp.controller;

import com.springstarter.journalApp.entity.journalEntry;
import com.springstarter.journalApp.entity.User;
import com.springstarter.journalApp.services.journalEntryService;
import com.springstarter.journalApp.services.userService;
import org.apache.catalina.security.SecurityUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    private journalEntryService journalEntryService;

    @Autowired
    private userService userService;
    @GetMapping
    public ResponseEntity<?> getAllInformationOfUser()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User user=userService.findByUserName(userName);
       List<journalEntry> all= user.getJournalEntries();
       if(all!=null && !all.isEmpty())
       {
           return new ResponseEntity<>(all,HttpStatus.OK);
       }
       else
           return new ResponseEntity<>(all,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry)
    {
        try {
                Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
                journalEntryService.saveJournalEntry(myEntry, authentication.getName());
                return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
            } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJournalEntry(@RequestBody journalEntry newEntry, @PathVariable ObjectId id)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<journalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {
            Optional<journalEntry> journalEntry= journalEntryService.findbyId(id);
            if(journalEntry.isPresent()){
                journalEntry oldEntry=journalEntry.get();
                oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent() : oldEntry.getContent());
                journalEntryService.saveEntry(oldEntry);
                return new ResponseEntity<>(oldEntry,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //get mapping with id

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId id)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<journalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<journalEntry> journalEntry= journalEntryService.findbyId(id);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        boolean deleted=journalEntryService.deleteById(id,userName);
        if(deleted)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
