package com.microservice.usersservice.dto;

public record UserDto(Long id, String nombre, String email, String rol) {
}