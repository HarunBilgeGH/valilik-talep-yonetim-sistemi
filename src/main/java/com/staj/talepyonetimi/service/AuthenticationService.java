package com.staj.talepyonetimi.service;

import java.util.Optional;

import com.staj.talepyonetimi.model.User;
import com.staj.talepyonetimi.repository.UserRepository;

public class AuthenticationService {
    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        if (!user.isActive() || user.getPassword() == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }
}
