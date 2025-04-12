package com.microservice.registration.service;

import com.microservice.registration.dto.RegistrationDto; // Cambiado de RegistrationDTO a RegistrationDto
import com.microservice.registration.dto.RegistrationResponseDto; // Cambiado de RegistrationResponseDTO a RegistrationResponseDto
import com.microservice.registration.entity.Registration;
import com.microservice.registration.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public String registerStudent(String userId, String subjectId) { // Ambos son String
        Registration registration = new Registration();
        registration.setUserId(userId); // Cambiado a setUser Id
        registration.setSubjectId(subjectId); // Cambiado a setSubjectId
        registrationRepository.save(registration);
        return "Student registered successfully!";
    }

    public List<RegistrationResponseDto> listAll() {
        return registrationRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<RegistrationResponseDto> findByStudent(String userId) { // Cambiado a String
        return registrationRepository.findByUserId(userId).stream() // Cambiado a findByUser Id
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<RegistrationResponseDto> findBySubject(String subjectId) { // Cambiado a String
        return registrationRepository.findBySubjectId(subjectId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private RegistrationResponseDto convertToResponseDto(Registration registration) {
        RegistrationResponseDto responseDto = new RegistrationResponseDto();
        responseDto.setId(registration.getId());
        responseDto.setUser (new RegistrationResponseDto.UserDto());
        responseDto.getUser ().setId(registration.getUserId()); // Cambiado a getUser Id
        responseDto.getUser ().setName("User  Name Placeholder"); // Aquí deberías obtener el nombre real del usuario
        responseDto.setSubject(new RegistrationResponseDto.SubjectDto());
        responseDto.getSubject().setId(registration.getSubjectId()); // Cambiado a getSubjectId
        responseDto.getSubject().setName("Subject Name Placeholder"); // Aquí deberías obtener el nombre real de la asignatura
        return responseDto;
    }
}