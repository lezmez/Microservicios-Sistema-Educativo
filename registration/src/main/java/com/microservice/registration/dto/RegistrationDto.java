package com.microservice.registration.dto;

import lombok.Data;

@Data
public class RegistrationDto { // Cambiado de RegistrationDTO a RegistrationDto
    private String userId; // ID del usuario como String
    private String subjectId; // ID de la asignatura como String
}