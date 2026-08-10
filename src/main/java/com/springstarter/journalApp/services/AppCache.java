package com.springstarter.journalApp.services;

import com.springstarter.journalApp.repository.appCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springstarter.journalApp.entity.appCacheEntity;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private appCacheRepository appCacheRepository;

    public Map<String, String> APP_CACHE;

    @PostConstruct
    public void init() {
        APP_CACHE = new HashMap<>();
        List<appCacheEntity> all = appCacheRepository.findAll();
        for (appCacheEntity appCache : all) {
            APP_CACHE.put(appCache.getKey(), appCache.getValue());
        }

    }
}