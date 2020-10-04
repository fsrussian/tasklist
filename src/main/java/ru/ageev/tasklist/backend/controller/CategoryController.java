package ru.ageev.tasklist.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ageev.tasklist.backend.entity.Category;
import ru.ageev.tasklist.backend.repository.CategoryRepository;

import java.util.List;



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
}

