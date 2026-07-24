package com.staj.talepyonetimi.ui;

import java.util.List;

import com.staj.talepyonetimi.model.WorkRequest;

public class RequestPrinter {

    public void print(WorkRequest request) {
        if (request == null) {
            System.out.println("Talep bulunamadı.");
            return;
        }

        String creatorName = request.getCreatedBy() == null
            ? "-"
            : request.getCreatedBy().getFullName();

        String technicianName = request.getAssignedTo() == null
            ? "Atanmadı"
            : request.getAssignedTo().getFullName();

        String departmentName = request.getDepartment() == null
            ? "-"
            : request.getDepartment().getName();

        System.out.println("----------------------------------------");
        System.out.println("ID: " + request.getId());
        System.out.println(
            "Talep numarası: " + request.getRequestNumber()
        );
        System.out.println("Başlık: " + request.getTitle());
        System.out.println("Açıklama: " + request.getDescription());
        System.out.println("Kategori: " + request.getCategory());
        System.out.println("Öncelik: " + request.getPriority());
        System.out.println("Durum: " + request.getStatus());
        System.out.println("Oluşturan: " + creatorName);
        System.out.println("Atanan: " + technicianName);
        System.out.println("Departman: " + departmentName);
        System.out.println("Son tarih: " + request.getDeadline());
        System.out.println("----------------------------------------");
    }

    public void printAll(List<WorkRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            System.out.println("Gösterilecek talep bulunamadı.");
            return;
        }

        for (WorkRequest request : requests) {
            print(request);
        }
    }
}
