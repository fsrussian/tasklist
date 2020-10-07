package ru.ageev.tasklist.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.ageev.tasklist.backend.entity.Task;

import java.util.List;

public interface TaskServiceInterface {
    List<Task> findAll();

    Task add(Task task);

    Task update(Task task);

    void deleteById(Long id);

    Page findByParams(String text, Integer completed, Long priorityId, Long categoryId, PageRequest paging);

    Task findById(Long id);
}
