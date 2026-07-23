package com.staj.talepyonetimi.model;

public class Department {
    private String id;
    private String name;
    private boolean active;

    public Department() {

    }

    public Department(String id,String name,boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id=id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isActive() {
        return this.active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean hasSameId(Department other) {
        if (other == null) {
            return false;
        }
        return this.id != null && this.id.equals(other.id);
    }
}
