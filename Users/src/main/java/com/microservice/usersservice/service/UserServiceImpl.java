package com.microservice.usersservice.service;

import com.microservice.usersservice.model.User;
import com.microservice.usersservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User crearUsuario(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    @Override
    public User obtenerPorId(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public User obtenerPorEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
    }

    @Override
    public User resetAndCrearUsuarioTest() {
        userRepository.deleteAll();
        User user = new User();
        user.setNombre("Admin");
        user.setEmail("admin@escuela.com");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRol(User.Rol.ADMIN);
        return userRepository.save(user);
    }

    @Override
    public void eliminarUsuario(String id) {
        userRepository.deleteById(id); // Implementación del método
    }
}
