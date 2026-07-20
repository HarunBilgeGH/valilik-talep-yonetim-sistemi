package com.staj.talepyonetimi.enums;

public enum RequestStatus {
    OPEN("Açık"),
    CREATED("Oluşturuldu"),
    WAITING_FOR_APPROVAL("Onay Bekliyor"),
    APPROVED("Onaylandı"),
    ASSIGNED("Atandı"),
    IN_PROGRESS("İşlemde"),
    WAITING_FOR_PART("Parça Bekleniyor"),
    COMPLETED("Tamamlandı"),
    REJECTED("Reddeldi"),
    CANCELLED("İptal Edildi");

    private final String displayName;

    RequestStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean isTerminal() {
        return switch (this) {
            case COMPLETED, REJECTED, CANCELLED -> true;
            default -> false;
        };
    }
    public boolean isCompleted() {
        return this == COMPLETED;
    }
}
