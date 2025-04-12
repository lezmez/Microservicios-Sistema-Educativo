package com.microservice.registration.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "registrations")
public class Registration {
    @Id
    private String id; // ID de la matrícula
    private String userId; // ID del usuario como String
    private String subjectId; // ID de la asignatura como String
}