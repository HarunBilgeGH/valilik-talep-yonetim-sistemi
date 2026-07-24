package com.staj.talepyonetimi.ui;

import com.staj.talepyonetimi.model.User;

public class ConsoleMenu {

    private final LoginMenu loginMenu;
    private final PersonnelMenu personnelMenu;
    private final ManagerMenu managerMenu;
    private final TechnicianMenu technicianMenu;

    public ConsoleMenu(
            LoginMenu loginMenu,
            PersonnelMenu personnelMenu,
            ManagerMenu managerMenu,
            TechnicianMenu technicianMenu
    ) {
        this.loginMenu = loginMenu;
        this.personnelMenu = personnelMenu;
        this.managerMenu = managerMenu;
        this.technicianMenu = technicianMenu;
    }

    public void start() {
        while (true) {
            User user = loginMenu.show();

            if (user == null) {
                System.out.println("Uygulamadan çıkılıyor.");
                return;
            }

            System.out.println(
                "Hoş geldiniz, " + user.getFullName() + "."
            );

            switch (user.getRole()) {
                case PERSONNEL -> personnelMenu.show(user);
                case TECHNICIAN -> technicianMenu.show(user);
                case UNIT_MANAGER, ADMIN -> managerMenu.show(user);
            }
        }
    }
}
