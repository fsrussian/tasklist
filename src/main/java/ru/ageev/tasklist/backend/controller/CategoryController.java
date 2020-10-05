package ru.ageev.tasklist.backend.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ageev.tasklist.backend.entity.Category;
import ru.ageev.tasklist.backend.entity.Priority;
import ru.ageev.tasklist.backend.repository.CategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/category")
public class CategoryController {
    final
    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/test")
    public List<Category> test(){
        List<Category> priorityList = categoryRepository.findAll();
        return priorityList;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category){
        if (category.getId() != null && category.getId()!= 0)
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        if (category.getTitle() == null && category.getTitle().trim().length() == 0)
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.ok(categoryRepository.save(category));

    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category){
        if (category.getId() == null || category.getId()== 0)
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        if (category.getTitle() == null || category.getTitle().trim().length() == 0)
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category category  = null;
        try {
            category = categoryRepository.findById(id).get();
        }catch (NoSuchElementException e ){
            e.printStackTrace();
            return new ResponseEntity("id = "+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try {
            categoryRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e ){
            e.printStackTrace();
            return new ResponseEntity("id = "+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

