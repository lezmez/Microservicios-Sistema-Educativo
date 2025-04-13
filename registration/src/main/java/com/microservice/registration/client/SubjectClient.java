package com.microservice.registration.client;

import com.microservice.registration.dto.RegistrationResponseDto; // Cambiado de RegistrationResponseDTO a RegistrationResponseDto
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "subjects")
public interface SubjectClient {
    @GetMapping("/api/subjects/{id}")
    RegistrationResponseDto.SubjectDto getSubjectById(@PathVariable String id); // Cambiado de RegistrationResponseDTO.SubjectDTO a RegistrationResponseDto.SubjectDto
}