package com.springstarter.journalApp.repository;

import com.springstarter.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepository extends MongoRepository<User,ObjectId> {
User findByUserName(String userName);
void deleteByUserName(String userName);
}
