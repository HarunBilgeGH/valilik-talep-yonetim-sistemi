package com.staj.talepyonetimi.config;

import com.staj.talepyonetimi.repository.DepartmentRepository;
import com.staj.talepyonetimi.repository.RequestRepository;
import com.staj.talepyonetimi.repository.UserRepository;
import com.staj.talepyonetimi.repository.memory.InMemoryDepartmentRepository;
import com.staj.talepyonetimi.repository.memory.InMemoryRequestRepository;
import com.staj.talepyonetimi.repository.memory.InMemoryUserRepository;
import com.staj.talepyonetimi.seed.DataSeeder;
import com.staj.talepyonetimi.service.AuthenticationService;
import com.staj.talepyonetimi.service.RequestService;

public class ApplicationContext {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    private final AuthenticationService authenticationService;
    private final RequestService requestService;

    public ApplicationContext() {
        this.departmentRepository =
            new InMemoryDepartmentRepository();

        this.userRepository =
            new InMemoryUserRepository();

        this.requestRepository =
            new InMemoryRequestRepository();

        this.authenticationService =
            new AuthenticationService(userRepository);

        this.requestService =
            new RequestService(requestRepository);

        DataSeeder dataSeeder = new DataSeeder(
            departmentRepository,
            userRepository
        );

        dataSeeder.seed();
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public RequestService getRequestService() {
        return requestService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public DepartmentRepository getDepartmentRepository() {
        return departmentRepository;
    }

    public RequestRepository getRequestRepository() {
        return requestRepository;
    }
}