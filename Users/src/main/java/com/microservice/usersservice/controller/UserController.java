package com.microservice.usersservice.controller;

import com.microservice.usersservice.model.User;
import com.microservice.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<User> crearUsuario(@RequestBody User user) {
        return ResponseEntity.ok(userService.crearUsuario(user));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE', 'ESTUDIANTE')")
    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios() {
        return ResponseEntity.ok(userService.listarTodos());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUsuario(@PathVariable String id) {
        return ResponseEntity.ok(userService.obtenerPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String id) {
        userService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}