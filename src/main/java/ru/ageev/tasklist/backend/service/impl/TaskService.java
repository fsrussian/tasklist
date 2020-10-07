package ru.ageev.tasklist.backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ageev.tasklist.backend.entity.Task;
import ru.ageev.tasklist.backend.repository.TaskRepository;
import ru.ageev.tasklist.backend.service.TaskServiceInterface;

import javax.transaction.Transactional;
import java.util.List;


@Service


@Transactional
public class TaskService implements TaskServiceInterface {

    private final TaskRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task add(Task task) {
        return repository.save(task);
    }

    @Override
    public Task update(Task task){
        return repository.save(task);
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }


    @Override
    public Page findByParams(String text, Integer completed, Long priorityId, Long categoryId, PageRequest paging){
        return repository.findByParams(text, completed, priorityId, categoryId, paging);
    }

    @Override
    public Task findById(Long id){
        return repository.findById(id).get();
    }


}
