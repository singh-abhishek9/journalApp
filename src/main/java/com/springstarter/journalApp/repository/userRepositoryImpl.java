package com.springstarter.journalApp.repository;

import com.springstarter.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class userRepositoryImpl {

    @Autowired
    private MongoTemplate MongoTemplate;

    public List<User> getUser(){
        Query query =new Query();

        query.addCriteria(Criteria.where("userName").is("Sachin"));
        List<User> users=MongoTemplate.find(query,User.class);
        return users;

    }
}
