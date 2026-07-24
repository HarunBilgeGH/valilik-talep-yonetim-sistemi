package com.staj.talepyonetimi.util;

public class RequestNumberGenerator {
    private final String prefix;
    private long currentId = 0;

    public RequestNumberGenerator(String prefix) {
        this.prefix  = prefix;
    }

    public String nextId() {
        this.currentId++;
        return String.format("%s-%04d", prefix , currentId);
    }
}

