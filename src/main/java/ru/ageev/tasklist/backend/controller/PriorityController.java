package ru.ageev.tasklist.backend.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ageev.tasklist.backend.entity.Category;
import ru.ageev.tasklist.backend.entity.Priority;
import ru.ageev.tasklist.backend.repository.PriorityRepository;
import ru.ageev.tasklist.backend.search.CategorySearchValues;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {
    final
    PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/test")
    public List<Priority> test(){
        List<Priority> priorityList = priorityRepository.findAll();
        return priorityList;
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority){
        if (priority.getId() != null && priority.getId()!= 0)
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0)
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        if (priority.getColor() == null || priority.getColor().trim().length() == 0)
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.ok(priorityRepository.save(priority));

    }
    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority){
        if (priority.getId() == null || priority.getId()== 0)
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0)
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        if (priority.getColor() == null || priority.getColor().trim().length() == 0)
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);

        return ResponseEntity.ok(priorityRepository.save(priority));
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id){
        Priority priority = null;
        try {
            priority = priorityRepository.findById(id).get();
        }catch (NoSuchElementException e ){
            e.printStackTrace();
            return new ResponseEntity("id = "+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try {
             priorityRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e ){
            e.printStackTrace();
            return new ResponseEntity("id = "+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Priority
            > findAll(){
        return priorityRepository.findAllByOrderByIdAsc();
    }
    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody CategorySearchValues searchValues){
        return ResponseEntity.ok(priorityRepository.findByTitle(searchValues.getText()));
    }
}
