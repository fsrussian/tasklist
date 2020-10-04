package ru.ageev.tasklist.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ageev.tasklist.backend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}