package com.staj.talepyonetimi.repository;

import java.util.List;
import java.util.Optional;

import com.staj.talepyonetimi.model.User;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    boolean existsByUsername(String username);

    void deleteById(String id);

    
}
