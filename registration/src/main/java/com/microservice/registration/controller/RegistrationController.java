package com.microservice.registration.controller;

import com.microservice.registration.dto.RegistrationDto; // Cambiado de RegistrationDTO a RegistrationDto
import com.microservice.registration.dto.RegistrationResponseDto; // Cambiado de RegistrationResponseDTO a RegistrationResponseDto
import com.microservice.registration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody RegistrationDto dto ) {
        return ResponseEntity.ok(registrationService.registerStudent(dto.getUserId(), dto.getSubjectId()));
    }

    @GetMapping
    public ResponseEntity<List<RegistrationResponseDto>> listAll() {
        return ResponseEntity.ok(registrationService.listAll());
    }

    @GetMapping("/student/{userId}")
    public ResponseEntity<List<RegistrationResponseDto>> findByStudent(@PathVariable String userId) {
        return ResponseEntity.ok(registrationService.findByStudent(userId));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<RegistrationResponseDto>> findBySubject(@PathVariable String subjectId) {
        return ResponseEntity.ok(registrationService.findBySubject(subjectId));
    }
}