package com.staj.talepyonetimi;

import java.util.Scanner;

import com.staj.talepyonetimi.config.ApplicationContext;
import com.staj.talepyonetimi.ui.ConsoleMenu;
import com.staj.talepyonetimi.ui.LoginMenu;
import com.staj.talepyonetimi.ui.ManagerMenu;
import com.staj.talepyonetimi.ui.PersonnelMenu;
import com.staj.talepyonetimi.ui.RequestPrinter;
import com.staj.talepyonetimi.ui.TechnicianMenu;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();

        try (Scanner scanner = new Scanner(System.in)) {
            RequestPrinter requestPrinter = new RequestPrinter();

            LoginMenu loginMenu = new LoginMenu(
                context.getAuthenticationService(),
                scanner
            );

            PersonnelMenu personnelMenu = new PersonnelMenu(
                context.getRequestService(),
                requestPrinter,
                scanner
            );

            ManagerMenu managerMenu = new ManagerMenu(
                context.getRequestService(),
                context.getUserRepository(),
                requestPrinter,
                scanner
            );

            TechnicianMenu technicianMenu = new TechnicianMenu(
                context.getRequestService(),
                requestPrinter,
                scanner
            );

            ConsoleMenu consoleMenu = new ConsoleMenu(
                loginMenu,
                personnelMenu,
                managerMenu,
                technicianMenu
            );

            consoleMenu.start();
        }
    }
}
