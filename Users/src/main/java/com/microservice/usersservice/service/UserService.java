package com.microservice.usersservice.service;

import com.microservice.usersservice.model.User;

import java.util.List;

public interface UserService {
    User crearUsuario(User user);
    List<User> listarTodos();
    User obtenerPorId(String id);
    User obtenerPorEmail(String email);
    User resetAndCrearUsuarioTest();
    void eliminarUsuario(String id);
}