package ru.ageev.tasklist.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ageev.tasklist.backend.entity.Priority;
import ru.ageev.tasklist.backend.repository.PriorityRepository;

import java.util.List;

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
}
