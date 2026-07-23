package com.staj.talepyonetimi.model;

import java.time.LocalDateTime;

public class Comment {
    private String id;
    private final String text;
    private final User author;
    private final LocalDateTime createdAt;

    public Comment(
        String id,
        String text,
        User author,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }
    public String getId() {
        return this.id; 
    }
    public void setId(String id) {
            this.id=id;
        }
    public String getText() {
        return this.text;
    }
    public User getAuthor() {
        return this.author;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public boolean isWrittenBy(String userId) {
        return this.author != null 
            && this.author.getId() != null 
            && userId != null
            && this.author.getId().equals(userId);
    }
}
