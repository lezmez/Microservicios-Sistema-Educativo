package com.microservice.usersservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("9XzQ4K7wL9IzR5aE8cD7fG6hJ4mK3nL2pO1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM3lN2oP1iN0bM9aC8dE7fG5hJ4kM")
    private String secretKey; // La nueva clave secreta se inyectará aquí

    // Extraer el nombre de usuario del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extraer un reclamo específico del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraer todos los reclamos del token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtener la clave de firma
    private Key getSignInKey() {
        byte[] keyBytes = secretKey.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generar un token para un usuario
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Generar un token con reclamos adicionales
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 horas
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Especificar el algoritmo de firma
                .compact();
    }

    // Verificar si el token es válido
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extraer la fecha de expiración del token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}