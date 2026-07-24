package com.staj.talepyonetimi.ui;

import java.util.List;
import java.util.Scanner;

import com.staj.talepyonetimi.enums.RequestCategory;
import com.staj.talepyonetimi.enums.RequestPriority;
import com.staj.talepyonetimi.model.User;
import com.staj.talepyonetimi.model.WorkRequest;
import com.staj.talepyonetimi.service.RequestService;

public class PersonnelMenu {

    private final RequestService requestService;
    private final RequestPrinter requestPrinter;
    private final Scanner scanner;

    public PersonnelMenu(
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
            System.out.println("=== PERSONEL MENÜSÜ ===");
            System.out.println("1- Yeni talep oluştur");
            System.out.println("2- Taleplerimi listele");
            System.out.println("0- Çıkış yap");
            System.out.print("Seçiminiz: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> createRequest(currentUser);
                case "2" -> listRequests(currentUser);
                case "0" -> {
                    System.out.println("Personel menüsünden çıkılıyor.");
                    return;
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    private void createRequest(User currentUser) {
        System.out.print("Talep başlığı: ");
        String title = scanner.nextLine();

        System.out.print("Talep açıklaması: ");
        String description = scanner.nextLine();

        RequestCategory category = readCategory();
        RequestPriority priority = readPriority();

        WorkRequest request = requestService.createRequest(
            title,
            description,
            category,
            priority,
            currentUser
        );

        if (request == null) {
            System.out.println(
                "Talep oluşturulamadı. Bilgileri kontrol edin."
            );
            return;
        }

        System.out.println("Talep başarıyla oluşturuldu.");
        requestPrinter.print(request);
    }

    private void listRequests(User currentUser) {
        List<WorkRequest> requests =
            requestService.findByCreator(currentUser.getId());

        requestPrinter.printAll(requests);
    }

    private RequestCategory readCategory() {
        RequestCategory[] categories = RequestCategory.values();

        while (true) {
            System.out.println();
            System.out.println("Talep kategorisini seçin:");

            for (int i = 0; i < categories.length; i++) {
                System.out.println(
                    (i + 1) + "- " + categories[i].getDisplayName()
                );
            }

            System.out.print("Seçiminiz: ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= categories.length) {
                    return categories[choice - 1];
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Geçerli bir kategori seçin.");
            }

        }
    }

    private RequestPriority readPriority() {
        RequestPriority[] priorities = RequestPriority.values();

        while (true) {
            System.out.println();
            System.out.println("Talep önceliğini seçin:");

            for (int i = 0; i < priorities.length; i++) {
                System.out.println(
                    (i + 1) + "- " + priorities[i].getDisplayName()
                );
            }

            System.out.print("Seçiminiz: ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= priorities.length) {
                    return priorities[choice - 1];
                }
            } catch (NumberFormatException ignored) {
                System.out.println("Geçerli bir öncelik seçin.");
            }

        }
    }
}
