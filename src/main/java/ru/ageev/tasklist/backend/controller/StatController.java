package ru.ageev.tasklist.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ageev.tasklist.backend.entity.Stat;
import ru.ageev.tasklist.backend.service.impl.StatService;

@RestController
public class StatController {

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    private final Long defaultId = 1l;


    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {

        return  ResponseEntity.ok(statService.findById(defaultId));
    }


}
