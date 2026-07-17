package com.staj.talepyonetimi.model;

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
    private List<StatusHistory> statusHistory;

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

    /*
    getId()
    getRequestNumber()
    getTitle()
    getDescription()
    getCategory()
    getPriority()
    getStatus()
    getCreatedBy()
    getAssignedTo()
    getDepartment()
    getCreatedAt()
    getUpdatedAt()
    getDeadline()
    getCompletedAt()
    getComments()
    getStatusHistory()
    public boolean isCompleted();
    public boolean isTerminal();
    public boolean isOverdue(Clock clock);
    public boolean isCreatedBy(long userId);
    public boolean isAssignedTo(long userId);
    public boolean belongsToDepartment(long departmentId);
    public void assignTo(
            User technician,
            LocalDateTime assignedAt
    );

    public void transitionTo(
            RequestStatus newStatus,
            LocalDateTime changedAt
    );

    public void addComment(Comment comment);

    public void addStatusHistory(StatusHistory history);
 */
}