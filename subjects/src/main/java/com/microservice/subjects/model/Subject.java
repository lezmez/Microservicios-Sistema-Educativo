package com.microservice.subjects.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "subjects") // Nombre de la colección en MongoDB
@Data
public class Subject {
    @Id
    private String subjectId; // ID del sujeto (cambiar a String para MongoDB)

    private String subjectCode; // Código del sujeto
    private String subjectName; // Nombre del sujeto
    private Integer subjectCredits; // Créditos del sujeto
}