package com.yash.skypulse_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "feed_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedItem {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String type;
    private String title;
    private String author;
    private String authorRole;
    private String timestamp;
    private String content;
    private String imageUrl;
    private int likesCount;
    private int commentsCount;
    private boolean likedByMe;
}