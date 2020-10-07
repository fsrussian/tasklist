package ru.ageev.tasklist.backend.service;


import ru.ageev.tasklist.backend.model.User;

import java.util.List;


public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
