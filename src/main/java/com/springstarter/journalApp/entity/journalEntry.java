package com.springstarter.journalApp.entity;

import com.springstarter.journalApp.enums.Sentiment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class journalEntry {
    @Id
    private ObjectId id;
    private LocalDateTime date;
    private String title;
    private String content;
    private Sentiment sentiment;
}
