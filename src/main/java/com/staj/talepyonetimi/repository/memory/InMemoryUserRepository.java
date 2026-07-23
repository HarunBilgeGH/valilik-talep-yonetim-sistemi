package com.staj.talepyonetimi.repository.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.staj.talepyonetimi.model.User;
import com.staj.talepyonetimi.repository.UserRepository;
import com.staj.talepyonetimi.util.IdGenerator;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private final IdGenerator idGenerator = new IdGenerator("U");

    @Override
    public Optional<User> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }

        for (User user : users) {
            if (user.getId() != null && user.getId().equals(id)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(users);
    }

    @Override
    public boolean existsByUsername(String username) {
        return findByUsername(username).isPresent();
    }

    @Override
    public void deleteById(String id) {
        if (id == null) {
            return;
        }
        users.removeIf(user -> user.getId() != null && user.getId().equals(id));
    }

    @Override
    public User save(User user) {
        if (user == null) {
            return null;
        }


        if (user.getId() == null) {
            user.setId(idGenerator.nextId());
            this.users.add(user);
            return user;
        }

        for (int i = 0; i < users.size(); i++) {
            User existingUser = users.get(i);

            if (existingUser.getId() != null && existingUser.getId().equals(user.getId())) {
                users.set(i, user);
                return user;
            }
        }

        this.users.add(user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {

        if (username == null) {
            return Optional.empty();
        }
        
        for (User user : users) {
            if (user.getUsername() != null && user.getUsername().equalsIgnoreCase(username)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

}
