package ru.ageev.tasklist.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ageev.tasklist.backend.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
