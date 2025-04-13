package com.microservice.registration.client;

import com.microservice.registration.dto.RegistrationResponseDto; // Cambiado de RegistrationResponseDTO a RegistrationResponseDto
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users")
public interface UserClient {
    @GetMapping("/api/users/{id}")
    RegistrationResponseDto.UserDto getUserById(@PathVariable String id); // Cambiado de RegistrationResponseDTO.UserDTO a RegistrationResponseDto.UserDto
}