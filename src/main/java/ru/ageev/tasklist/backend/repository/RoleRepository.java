package ru.ageev.tasklist.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ageev.tasklist.backend.model.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
