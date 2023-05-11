package com.doge.index.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "doge_pic")
@Data
public class Doge {
    @Id
    private String id;
    private String url;
    private String userId;
    private String driveId;
    private String mimeType;
    private String title;
    private String description;
    private List<String> keywords;
    private String messageId;
    // getters and setters
}