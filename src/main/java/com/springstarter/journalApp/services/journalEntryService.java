package com.springstarter.journalApp.services;

import com.springstarter.journalApp.entity.User;
import com.springstarter.journalApp.entity.journalEntry;
import com.springstarter.journalApp.repository.journalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class journalEntryService {

    @Autowired
    private journalEntryRepository journalEntryRepository;
    @Autowired
    private userService userService;
    @Transactional
    public void saveJournalEntry(journalEntry journalEntry,String userName)
    {
        User newUser=userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        journalEntry saved=journalEntryRepository.save(journalEntry);
        newUser.getJournalEntries().add(saved);
        userService.saveEntry(newUser);
    }

    public Optional<journalEntry> findbyId(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    public List<journalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    @Transactional
    public boolean deleteById(ObjectId id,String userName)
    {
        boolean removed=false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An Exception occured while deleting the details", e);
        }
        return removed;
    }

    public void saveEntry(journalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
}
