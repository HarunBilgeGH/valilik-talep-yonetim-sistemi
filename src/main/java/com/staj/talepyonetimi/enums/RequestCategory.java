package com.staj.talepyonetimi.enums;

public enum RequestCategory {
    INFORMATION_TECHNOLOGY("Teknoloji Bilgisi"),
    PRINTER("Yazıcı"),
    INTERNET("İnternet"),
    CLEANING("Temizlik"),
    ELECTRICITY("Elektrik"),
    PLUMBING("Tesisat"),
    OFFICE_SUPPLIES("Ofis Eşyaları"),
    OTHER("Diğer"),
    MAINTENANCE("Bakım");
    private final String displayName;

    RequestCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}