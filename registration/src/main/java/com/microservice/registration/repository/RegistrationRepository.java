package com.microservice.registration.repository;

import com.microservice.registration.entity.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends MongoRepository<Registration, String> {
    List<Registration> findByUserId(String userId); // Cambiado de findByUser  Id a findByUser Id
    List<Registration> findBySubjectId(String subjectId);
}