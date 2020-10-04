package ru.ageev.tasklist.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ageev.tasklist.backend.entity.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
