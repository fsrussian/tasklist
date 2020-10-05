package ru.ageev.tasklist.backend.service;

import org.springframework.stereotype.Service;
import ru.ageev.tasklist.backend.entity.Stat;
import ru.ageev.tasklist.backend.repository.StatRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatService {

    private final StatRepository repository;

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findById(Long id){
        return repository.findById(id).get();
    }


}
