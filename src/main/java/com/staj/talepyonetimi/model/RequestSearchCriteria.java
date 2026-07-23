package com.staj.talepyonetimi.model;

import com.staj.talepyonetimi.enums.RequestCategory;
import com.staj.talepyonetimi.enums.RequestPriority;
import com.staj.talepyonetimi.enums.RequestStatus;

public class RequestSearchCriteria {
    private RequestStatus status;
    private RequestPriority priority;
    private RequestCategory category;
    private String departmentId;
    private String assignedUserId;
    private String createdByUserId;
    private String keyword;
    private Boolean overdue;

    public RequestSearchCriteria(
        RequestStatus status,
        RequestPriority priority,
        RequestCategory category,
        String departmentId,
        String assignedUserId,
        String createdByUserId,
        String keyword,
        Boolean overdue
    ) {
        this.status = status;
        this.priority = priority;
        this.category = category;
        this.departmentId = departmentId;
        this.assignedUserId = assignedUserId;
        this.createdByUserId = createdByUserId;
        this.keyword = keyword;
        this.overdue = overdue;
    }

    public RequestSearchCriteria() {
        this (null,null,null,null,null,null,null,null);
    }

    public RequestStatus getStatus() {
        return this.status;
    }
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RequestPriority getPriority() {
        return this.priority;
    }
    public void setPriority(RequestPriority priority) {
        this.priority = priority;
    }

    public RequestCategory getCategory() {
        return this.category;
    }
    public void setCategory(RequestCategory category) {
        this.category = category;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getAssignedUserId() {
        return this.assignedUserId;
    }
    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public String getCreatedByUserId() {
        return this.createdByUserId;
    }
    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getKeyword() {
        return this.keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getOverdue() {
        return this.overdue;
    }
    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }

    public RequestSearchCriteria status(RequestStatus status) {
        this.status = status;
        return this;
    }
    public RequestSearchCriteria priority(RequestPriority priority) {
        this.priority = priority;
        return this;
    }
    public RequestSearchCriteria keyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
