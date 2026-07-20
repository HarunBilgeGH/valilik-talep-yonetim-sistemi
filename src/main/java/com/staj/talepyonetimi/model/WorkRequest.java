package com.staj.talepyonetimi.model;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.staj.talepyonetimi.enums.RequestCategory;
import com.staj.talepyonetimi.enums.RequestPriority;
import com.staj.talepyonetimi.enums.RequestStatus;

public class WorkRequest {
    private Long id;
    private String requestNumber;
    private String title;
    private String description;

    private RequestCategory category;
    private RequestPriority priority;
    private RequestStatus status;

    private User createdBy;
    private User assignedTo;
    private Department department;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deadline;
    private LocalDateTime completedAt;

    private List<Comment> comments;
    private List<RequestStatusHistory> statusHistory;

    public WorkRequest() {
        this.status = RequestStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.comments = new ArrayList<>();
        this.statusHistory = new ArrayList<>();
    }

    public WorkRequest(
            String requestNumber,
            String title,
            String description,
            RequestCategory category,
            RequestPriority priority,
            User createdBy,
            Department department
    ) {
        this();

        this.requestNumber = requestNumber;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.createdBy = createdBy;
        this.department = department;

        if (priority != null) {
            this.deadline = this.createdAt.plus(priority.getDuration());
        }
    }

    
    public Long getId() {
        return this.id;
    }
    public String getRequestNumber() {
        return this.requestNumber;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public RequestCategory getCategory() {
        return this.category;
    }
    public RequestPriority getPriority() {
        return this.priority;
    }
    public RequestStatus getStatus() {
        return this.status;
    }
    public User getCreatedBy() {
        return this.createdBy;
    }
    public User getAssignedTo() {
        return this.assignedTo;
    }
    public Department getDepartment() {
        return this.department;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
    public LocalDateTime getDeadline() {
        return this.deadline;
    }
    public LocalDateTime getCompletedAt() {
        return this.completedAt;
    }
    public List<Comment> getComments() {
        return List.copyOf(comments);
    }
    public List<RequestStatusHistory> getStatusHistory() {
        return List.copyOf(statusHistory);
    }

    public boolean isCompleted() {
        return this.status.isCompleted();
    }
    public boolean isTerminal() {
        return this.status.isTerminal();
    }
    public boolean isOverdue() {
        if (!this.status.isTerminal()) {
            return this.deadline != null && LocalDateTime.now().isAfter(this.deadline);
        }
        return false;
    }
    public boolean isAssignedTo(long userId) {
        return this.assignedTo != null
            && this.assignedTo.getId() != null
            && this.assignedTo.getId().equals(userId);
    }
    public boolean belongsToDepartment(long departmentId) {
        return this.department != null 
            && this.department.getId() != null
            && this.department.getId().equals(departmentId); 
            
    }
        public void assignTo(User technician, LocalDateTime assignedAt) {
        if (technician == null || this.status.isTerminal() || !technician.isTechnician()) {
            return;
        }

        this.assignedTo = technician;
        this.status = RequestStatus.ASSIGNED;
        this.updatedAt = assignedAt != null ? assignedAt : LocalDateTime.now();
    }

    public void transitionTo(RequestStatus newStatus,User changedBy,LocalDateTime changedAt) {
        if (newStatus == null || this.status.isTerminal() || this.status == newStatus) {
            return;
        }
        RequestStatus oldStatus = this.status;
        LocalDateTime changeTime = changedAt != null ? changedAt : LocalDateTime.now();

        this.status = newStatus;
        this.updatedAt = changeTime;

        if (newStatus == RequestStatus.COMPLETED) {
            this.completedAt = changeTime;
        }

        addStatusHistory(oldStatus, newStatus, changedBy, changeTime);

    }

    public void addComment(Comment comment) {
        if (comment != null) {
            this.comments.add(comment);
            this.updatedAt = LocalDateTime.now();
        }
    }

    private void addStatusHistory(RequestStatus oldStatus,
        RequestStatus newStatus,
        User changedBy,
        LocalDateTime changedAt
    ) {
        RequestStatusHistory history = new RequestStatusHistory(oldStatus,newStatus,changedBy,changedAt);
        this.statusHistory.add(history); 
    }
 
}