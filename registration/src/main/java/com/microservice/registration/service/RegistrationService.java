package com.microservice.registration.service;

// Cliente para obtener información de la asignatura
import com.microservice.registration.client.SubjectClient;
import com.microservice.registration.client.UserClient;
// Cliente para obtener información del usuario
import com.microservice.registration.dto.RegistrationResponseDto; // DTO de respuesta
import com.microservice.registration.entity.Registration; // Entidad de registro
import com.microservice.registration.repository.RegistrationRepository; // Repositorio de registros
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserClient usuarioClient; // Cliente para obtener información del usuario
    private final SubjectClient asignaturaClient; // Cliente para obtener información de la asignatura

    public String registerStudent(String userId, String subjectId) { // Ambos son String
        // Obtener información del usuario y de la asignatura
        RegistrationResponseDto.UserDto usuario = usuarioClient.getUserById(userId);
        RegistrationResponseDto.SubjectDto asignatura = asignaturaClient.getSubjectById(subjectId);

        // Crear un nuevo registro
        Registration registration = new Registration();
        registration.setUserId(userId); // Cambiado a setUser Id
        registration.setSubjectId(subjectId); // Cambiado a setSubjectId
        registrationRepository.save(registration);

        return "Student " + usuario.getNombre() + " registered in " + asignatura.getSubjectName(); // Mensaje de éxito
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

        // Obtener el nombre real del usuario
        RegistrationResponseDto.UserDto usuario = usuarioClient.getUserById(registration.getUserId());
        responseDto.getUser ().setNombre(usuario.getNombre()); // Obtener el nombre real del usuario

        responseDto.setSubject(new RegistrationResponseDto.SubjectDto());
        responseDto.getSubject().setId(registration.getSubjectId()); // Cambiado a getSubjectId

        // Obtener el nombre real de la asignatura
        RegistrationResponseDto.SubjectDto asignatura = asignaturaClient.getSubjectById(registration.getSubjectId());
        responseDto.getSubject().setSubjectName(asignatura.getSubjectName()); // Obtener el nombre real de la asignatura

        return responseDto;
    }
}