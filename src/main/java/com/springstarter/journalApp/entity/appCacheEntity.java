package com.springstarter.journalApp.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="APP_CACHE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class appCacheEntity {
    private String key;
    private String value;
}
