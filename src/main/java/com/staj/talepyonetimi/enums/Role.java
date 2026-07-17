package com.staj.talepyonetimi.enums;

public enum Role {
    ADMIN("Sistem Yöneticisi"),
    UNIT_MANAGER("Birim Yöneticisi"),
    PERSONNEL("Personel"),
    USER("Kullanıcı");
    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}