package com.staj.talepyonetimi.ui;

import java.util.Scanner;

import com.staj.talepyonetimi.model.User;
import com.staj.talepyonetimi.service.AuthenticationService;

public class LoginMenu {

    private final AuthenticationService authenticationService;
    private final Scanner scanner;

    public LoginMenu(
            AuthenticationService authenticationService,
            Scanner scanner
    ) {
        this.authenticationService = authenticationService;
        this.scanner = scanner;
    }

    public User show() {
        while (true) {
            System.out.println();
            System.out.println("=== GİRİŞ ===");
            System.out.println("Çıkış yapmak için 0 yazın.");
            System.out.print("Kullanıcı adı: ");

            String userName = scanner.nextLine().trim();

            if ("0".equals(userName)) {
                return null;
            }

            if (userName.isBlank()) {
                System.out.println("Kullanıcı adı boş olamaz.");
                continue;
            }

            System.out.print("Şifre: ");
            String password = scanner.nextLine();

            User loggedInUser = authenticationService.login(
                userName,
                password
            );

            if (loggedInUser != null) {
                return loggedInUser;
            }

            System.out.println("Kullanıcı adı veya şifre hatalı.");
        }
    }
}
