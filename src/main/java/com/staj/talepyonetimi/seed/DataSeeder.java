package com.staj.talepyonetimi.seed;

import com.staj.talepyonetimi.enums.Role;
import com.staj.talepyonetimi.model.Department;
import com.staj.talepyonetimi.model.User;
import com.staj.talepyonetimi.repository.DepartmentRepository;
import com.staj.talepyonetimi.repository.UserRepository;

public class DataSeeder {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DataSeeder(
            DepartmentRepository departmentRepository,
            UserRepository userRepository
    ) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    public void seed() {
        if (!userRepository.findAll().isEmpty()) {
            return;
        }

        Department informationTechnology = new Department(
            null,
            "Bilgi İşlem",
            true
        );

        departmentRepository.save(informationTechnology);

        User personnel = new User(
            null,
            "personel",
            "1234",
            "Ayşe Personel",
            Role.PERSONNEL,
            informationTechnology,
            true
        );

        User technician = new User(
            null,
            "teknisyen",
            "1234",
            "Mehmet Teknisyen",
            Role.TECHNICIAN,
            informationTechnology,
            true
        );

        User manager = new User(
            null,
            "yonetici",
            "1234",
            "Ahmet Yönetici",
            Role.UNIT_MANAGER,
            informationTechnology,
            true
        );

        User admin = new User(
            null,
            "admin",
            "1234",
            "Sistem Yöneticisi",
            Role.ADMIN,
            informationTechnology,
            true
        );

        userRepository.save(personnel);
        userRepository.save(technician);
        userRepository.save(manager);
        userRepository.save(admin);
    }
}