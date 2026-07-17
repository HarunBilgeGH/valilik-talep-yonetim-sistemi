package com.staj.talepyonetimi.model;

import com.staj.talepyonetimi.enums.Role;

public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private String fullName;
    private Role role;
    private Department department;
    private Boolean active;

    public User(Long id, String username, String passwordHash, String fullName, Role role, Department department, Boolean active) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
        this.department = department;
        this.active = active;
    }
    public User() {

    }
    public Long getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.passwordHash;
    }
    public String getFullName() {
        return this.fullName;
    }
    public Role getRole() {
        return this.role;
    }
    public Department getDepartment() {
        return this.department;
    }
    public Boolean isActive() {
        return this.active;
    }

    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isAdmin() {
        return Role.ADMIN.equals(this.role);
    }
    public Boolean isManager() {
        return Role.UNIT_MANAGER.equals(this.role);
    }
    public Boolean isTechnician() {
        return Role.TECHNICIAN.equals(this.role);
    }
    public Boolean isPersonnel() {
        return Role.PERSONNEL.equals(this.role);
    }
    public Boolean belongsToDepartment(Long departmentId) {
        if (this.department == null || departmentId == null) {
            return false;
        }
        return this.department.getId().equals(departmentId);
    }
}
