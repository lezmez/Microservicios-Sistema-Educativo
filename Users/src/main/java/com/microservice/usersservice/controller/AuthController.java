package com.microservice.usersservice.controller;

import com.microservice.usersservice.dto.AuthRequest;
import com.microservice.usersservice.dto.AuthResponse;
import com.microservice.usersservice.model.User;
import com.microservice.usersservice.security.JwtService;
import com.microservice.usersservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new AuthResponse("Credenciales inválidas: " + e.getMessage()));
        }
        var token = jwtService.generateToken(userDetailsService.loadUserByUsername(request.email()));
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/registro")
    public ResponseEntity<User> registrarUsuario(@RequestBody User user) {
        return ResponseEntity.ok(userService.crearUsuario(user));
    }

    @PostMapping("/reset-test")
    public ResponseEntity<User> resetTestUser () {
        return ResponseEntity.ok(userService.resetAndCrearUsuarioTest());
    }
}