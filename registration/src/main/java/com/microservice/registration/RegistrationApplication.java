package com.microservice.registration; // Paquete de la aplicación

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // Habilita el descubrimiento de servicios
import org.springframework.cloud.openfeign.EnableFeignClients; // Habilita el uso de Feign para llamadas a otros servicios

@SpringBootApplication
@EnableDiscoveryClient // Habilita el cliente de descubrimiento (Eureka)
@EnableFeignClients(basePackages = "com.microservice.registration.client") // Habilita Feign en el paquete especificado
public class RegistrationApplication { // Clase principal de la aplicación
	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args); // Inicia la aplicación
	}
}