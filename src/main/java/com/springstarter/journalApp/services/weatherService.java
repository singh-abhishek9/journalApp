package com.springstarter.journalApp.services;

import com.springstarter.journalApp.api.response.weatherResponse;
import com.springstarter.journalApp.constants.placeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Component

public class weatherService {

    @Value("${api.api-key}")
    private String apiKey;

    @Autowired
    private AppCache AppCache;

    @Autowired
    private RestTemplate restTemplate;

    public weatherResponse getWeather(String city){
        String finalApi=AppCache.APP_CACHE.get("weather_api").replace(placeHolder.apiKey,apiKey).replace(placeHolder.city,city);
        ResponseEntity<weatherResponse> response=restTemplate.exchange(finalApi, HttpMethod.GET,null, weatherResponse.class);
        weatherResponse body=response.getBody();
        return body;
    }
}
