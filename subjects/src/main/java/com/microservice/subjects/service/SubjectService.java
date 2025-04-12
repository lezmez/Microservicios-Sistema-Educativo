package com.microservice.subjects.service;

import com.microservice.subjects.model.Subject;
import com.microservice.subjects.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> listAll() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getById(String id) {
        return subjectRepository.findById(id);
    }
}