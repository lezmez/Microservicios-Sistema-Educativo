package com.microservice.registration.dto;

import lombok.Data;

@Data
public class RegistrationResponseDto {
    private String id; // ID de la matrícula
    private UserDto user; // Información del usuario
    private SubjectDto subject; // Información de la asignatura

    @Data
    public static class UserDto {
        private String id; // ID del usuario como String
        private String nombre; // Nombre del usuario
    }

    @Data
    public static class SubjectDto {
        private String id; // ID de la asignatura como String
        private String subjectName; // Nombre de la asignatura
    }
}