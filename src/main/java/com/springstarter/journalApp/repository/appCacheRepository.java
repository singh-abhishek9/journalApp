package com.springstarter.journalApp.repository;

import com.springstarter.journalApp.entity.appCacheEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface appCacheRepository extends MongoRepository<appCacheEntity, ObjectId> {

}
