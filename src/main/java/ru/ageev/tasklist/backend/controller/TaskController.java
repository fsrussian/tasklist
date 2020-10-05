package ru.ageev.tasklist.backend.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ageev.tasklist.backend.entity.Category;
import ru.ageev.tasklist.backend.entity.Task;
import ru.ageev.tasklist.backend.repository.TaskRepository;
import ru.ageev.tasklist.backend.repository.TaskRepository;
import ru.ageev.tasklist.backend.search.CategorySearchValues;
import ru.ageev.tasklist.backend.search.TaskSearchValues;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {
    final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/test")
    public List<Task> test() {
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        if (task.getId() != null && task.getId() != 0)
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        if (task.getTitle() == null || task.getTitle().trim().length() == 0)
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.ok(taskRepository.save(task));

    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Task task) {
        if (task.getId() == null || task.getId() == 0)
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        if (task.getTitle() == null || task.getTitle().trim().length() == 0)
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        taskRepository.save(task);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {

        Task task  = null;
        try {
            task = taskRepository.findById(id).get();
        }catch (NoSuchElementException e ){
            e.printStackTrace();
            return new ResponseEntity("id = "+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepository.findById(id).get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {
        String text = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;

        Integer completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;

        Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;

        String sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
        String sortDirection = taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() : null;

        Integer pageNumber = taskSearchValues.getPageNumber() != null ? taskSearchValues.getPageNumber() : null;
        Integer pageSize = taskSearchValues.getPageSize() != null ? taskSearchValues.getPageSize() : null;


        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page result = taskRepository.findByParams(text, completed, priorityId, categoryId, pageRequest);

        return ResponseEntity.ok(result);
    }


}


