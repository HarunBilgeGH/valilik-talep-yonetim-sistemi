package com.staj.talepyonetimi.model;

import java.time.LocalDateTime;

import com.staj.talepyonetimi.enums.NotificationType;

public class Notification {
    private String id;
    private final User receiver;
    private final NotificationType type;
    private final String message;
    private final LocalDateTime createdAt;
    private boolean read = false;

    public Notification(
        String id,
        User receiver,
        NotificationType type,
        String message,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.receiver = receiver;
        this.type = type;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id=id;
    }
    public User getReceiver() {
        return this.receiver;
    }
    public NotificationType getType() {
        return this.type;
    }
    public String getMessage() {
        return this.message;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public boolean isRead() {
        return this.read;
    }

    public void markAsRead() {
        this.read = true;
    }
    public boolean belongsTo(String userId) {
        return receiver != null 
        && receiver.getId() != null 
        && userId != null 
        && receiver.getId().equals(userId);
    }
}
