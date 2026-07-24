package com.staj.talepyonetimi.ui;

import java.util.List;
import java.util.Scanner;

import com.staj.talepyonetimi.enums.RequestStatus;
import com.staj.talepyonetimi.model.User;
import com.staj.talepyonetimi.model.WorkRequest;
import com.staj.talepyonetimi.service.RequestService;

public class TechnicianMenu {

    private final RequestService requestService;
    private final RequestPrinter requestPrinter;
    private final Scanner scanner;

    public TechnicianMenu(
            RequestService requestService,
            RequestPrinter requestPrinter,
            Scanner scanner
    ) {
        this.requestService = requestService;
        this.requestPrinter = requestPrinter;
        this.scanner = scanner;
    }

    public void show(User currentUser) {
        if (currentUser == null) {
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("=== TEKNİSYEN MENÜSÜ ===");
            System.out.println("1- Bana atanan talepleri listele");
            System.out.println("2- Talebi işleme al");
            System.out.println("3- Talebi tamamla");
            System.out.println("0- Çıkış yap");
            System.out.print("Seçiminiz: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> listAssignedRequests(currentUser);
                case "2" -> changeStatus(
                    currentUser,
                    RequestStatus.IN_PROGRESS
                );
                case "3" -> changeStatus(
                    currentUser,
                    RequestStatus.COMPLETED
                );
                case "0" -> {
                    System.out.println("Teknisyen menüsünden çıkılıyor.");
                    return;
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void listAssignedRequests(User currentUser) {
        List<WorkRequest> requests =
            requestService.findByTechnician(currentUser.getId());

        requestPrinter.printAll(requests);
    }

    private void changeStatus(
            User currentUser,
            RequestStatus newStatus
    ) {
        listAssignedRequests(currentUser);

        if (requestService
                .findByTechnician(currentUser.getId())
                .isEmpty()) {
            return;
        }

        System.out.print("Talep numarası: ");
        String requestNumber = scanner.nextLine().trim();

        WorkRequest request = requestService.changeStatus(
            requestNumber,
            newStatus,
            currentUser
        );

        if (request == null) {
            System.out.println(
                "Talep durumu değiştirilemedi. Durum geçişini kontrol edin."
            );
            return;
        }

        System.out.println("Talep durumu güncellendi.");
        requestPrinter.print(request);
    }
}
