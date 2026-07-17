package com.staj.talepyonetimi.enums;

import java.time.Duration;

public enum RequestPriority {
    LOW("Az",Duration.ofDays(7)),
    MEDIUM("Orta",Duration.ofDays(3)),
    HIGH("Yüksek",Duration.ofDays(1)),
    CRITICAL("Kritik",Duration.ofHours(4));
    private final String displayName;
    private final Duration duration;

    RequestPriority(String displayName, Duration duration) {
        this.displayName = displayName;
        this.duration = duration;
    }

    public String getDisplayName() {
        return this.displayName;
    }
    public Duration getDruation() {
        return this.duration;
    }

}