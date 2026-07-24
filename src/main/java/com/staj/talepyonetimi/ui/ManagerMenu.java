package com.staj.talepyonetimi.ui;

import java.util.List;
import java.util.Scanner;

import com.staj.talepyonetimi.model.User;
import com.staj.talepyonetimi.model.WorkRequest;
import com.staj.talepyonetimi.repository.UserRepository;
import com.staj.talepyonetimi.service.RequestService;

public class ManagerMenu {

    private final RequestService requestService;
    private final UserRepository userRepository;
    private final RequestPrinter requestPrinter;
    private final Scanner scanner;

    public ManagerMenu(
            RequestService requestService,
            UserRepository userRepository,
            RequestPrinter requestPrinter,
            Scanner scanner
    ) {
        this.requestService = requestService;
        this.userRepository = userRepository;
        this.requestPrinter = requestPrinter;
        this.scanner = scanner;
    }

    public void show(User currentUser) {
        if (currentUser == null) {
            return;
        }

        while (true) {
            System.out.println();
            System.out.println("=== YÖNETİCİ MENÜSÜ ===");
            System.out.println("1- Bütün talepleri listele");
            System.out.println("2- Teknisyenleri listele");
            System.out.println("3- Talebi teknisyene ata");
            System.out.println("0- Çıkış yap");
            System.out.print("Seçiminiz: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> listRequests();
                case "2" -> listTechnicians();
                case "3" -> assignRequest(currentUser);
                case "0" -> {
                    System.out.println("Yönetici menüsünden çıkılıyor.");
                    return;
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void listRequests() {
        requestPrinter.printAll(requestService.findAll());
    }

    private void listTechnicians() {
        List<User> users = userRepository.findAll();
        boolean technicianFound = false;

        System.out.println();
        System.out.println("=== AKTİF TEKNİSYENLER ===");

        for (User user : users) {
            if (Boolean.TRUE.equals(user.isActive())
                    && Boolean.TRUE.equals(user.isTechnician())) {
                technicianFound = true;

                String departmentName = user.getDepartment() == null
                    ? "-"
                    : user.getDepartment().getName();

                System.out.println(
                    user.getId()
                        + " - "
                        + user.getFullName()
                        + " - "
                        + departmentName
                );
            }
        }

        if (!technicianFound) {
            System.out.println("Aktif teknisyen bulunamadı.");
        }
    }

    private void assignRequest(User currentUser) {
        listRequests();

        if (requestService.findAll().isEmpty()) {
            return;
        }

        System.out.print("Talep numarası: ");
        String requestNumber = scanner.nextLine().trim();

        listTechnicians();

        System.out.print("Teknisyen ID: ");
        String technicianId = scanner.nextLine().trim();

        User technician = userRepository
            .findById(technicianId)
            .orElse(null);

        if (technician == null) {
            System.out.println("Teknisyen bulunamadı.");
            return;
        }

        WorkRequest assignedRequest = requestService.assignRequest(
            requestNumber,
            technician,
            currentUser
        );

        if (assignedRequest == null) {
            System.out.println("Talep teknisyene atanamadı.");
            return;
        }

        System.out.println("Talep başarıyla teknisyene atandı.");
        requestPrinter.print(assignedRequest);
    }
}
