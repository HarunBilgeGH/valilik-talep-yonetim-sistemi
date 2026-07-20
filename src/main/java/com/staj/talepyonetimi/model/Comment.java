package com.staj.talepyonetimi.model;

import java.time.LocalDateTime;

public class Comment {
    private final Long id;
    private final String text;
    private final User author;
    private final LocalDateTime createdAt;

    public Comment(
        Long id,
        String text,
        User author,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }
    public long getId() {
        return this.id; 
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
    public boolean isWrittenBy(long userId) {
        return this.author != null 
            && this.author.getId() != null 
            && this.author.getId().equals(userId);
    }
}
