package com.staj.talepyonetimi.model;

import java.time.LocalDateTime;

import com.staj.talepyonetimi.enums.RequestStatus;

public class RequestStatusHistory {
    private String id;
    private final RequestStatus oldStatus;
    private final RequestStatus newStatus;
    private final User changedBy;
    private final LocalDateTime changedAt;
    private final String explanation;

    public RequestStatusHistory(
        String id, 
        RequestStatus oldStatus, 
        RequestStatus newStatus, 
        User changedBy, 
        LocalDateTime changedAt, 
        String explanation
    ) {
        this.id = id;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
        this.changedAt = changedAt;
        this.explanation = explanation;    
    }

    public RequestStatusHistory(
        RequestStatus oldStatus, 
        RequestStatus newStatus, 
        User changedBy, 
        LocalDateTime changedAt
    ) {
        this (null, oldStatus, newStatus, changedBy, changedAt, null);
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id=id;
    }
    public RequestStatus getOldStatus() {
        return this.oldStatus;
    }
    public RequestStatus getNewStatus() {
        return this.newStatus;
    }
    public User getChangedBy() {
        return this.changedBy;
    }
    public LocalDateTime getChangedAt() {
        return this.changedAt;
    }
    public String getExplanation() {
        return this.explanation;
    }
}
