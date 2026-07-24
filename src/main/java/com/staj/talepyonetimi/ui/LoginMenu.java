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
            String userName = scanner.nextLine();
            
            if (userName.equals("0")) {
                return null;
            }
            System.out.print("Password: ");
            String password = scanner.nextLine();
            User loggedInUser = authenticationService.login(userName, password);
            if (loggedInUser != null) {
                return loggedInUser;
            }
            else {
                System.out.println("Kullanıcı adı veya şifre hatalı.");
            }

        }
    }
}
