package ru.ageev.tasklist.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ageev.tasklist.backend.entity.Stat;
import ru.ageev.tasklist.backend.repository.StatRepository;

@RestController
public class StatController {
    private final StatRepository statRepository;
    private final Long defaultId = 1l;

    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {
        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }
}
