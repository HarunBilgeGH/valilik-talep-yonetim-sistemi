package com.staj.talepyonetimi.util;

public class IdGenerator {
    private final String prefix;
    private long currentId = 0;

    public IdGenerator(String prefix) {
        this.prefix  = prefix;
    }

    public String nextId() {
        this.currentId++;
        return String.format("%s-%04d", prefix , currentId);
    }
}
