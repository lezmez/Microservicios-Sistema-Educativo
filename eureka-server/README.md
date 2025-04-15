# Eureka Server - Microservice Discovery Service
## Descripción
Este proyecto implementa un servidor Eureka para el registro y descubrimiento de microservicios. Actúa como un servidor de registro central donde los microservicios pueden registrarse y descubrir otros servicios disponibles en el sistema.
## Características

- Registro centralizado de servicios
- Descubrimiento automático de microservicios
- Monitoreo de estado de servicios
- Endpoints de actuator para supervisión y métricas
- Dashboard web para visualizar los servicios registrados

## Requisitos previos

- Java 17 o superior
- Maven 3.9.x o superior
- Docker (opcional, para contenerización)

## Tecnologías utilizadas

- Spring Boot 3.3.10
- Spring Cloud 2023.0.5
- Netflix Eureka Server
- Spring Boot Actuator

## Configuración
### Propiedades principales
```
# Puerto del servidor Eureka
server.port=8761

# Nombre de la aplicación
spring.application.name=eureka-server

# Configuración del servidor Eureka
eureka.server.enable-self-preservation=false
eureka.server.eviction-interval-timer-in-ms=5000

# Configuración del cliente Eureka
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

# Configuración de instancia
eureka.instance.hostname=localhost
eureka.instance.prefer-ip-address=true

# Endpoints de actuator
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always

# Perfiles activos
spring.profiles.active=dev
```
## Instalación y ejecución
### Usando Maven
```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/eureka-server.git
cd eureka-server

# Compilar el proyecto
./mvnw clean install

# Ejecutar la aplicación
./mvnw spring-boot:run
```
Usando Docker
```bash
# Construir la imagen Docker
docker build -t eureka-server .

# Ejecutar el contenedor
docker run -p 8761:8761 eureka-server
```
## Uso
Una vez iniciado, el servidor Eureka estará disponible en:

- Dashboard de Eureka: http://localhost:8761
- API de servicios: http://localhost:8761/eureka/apps

### Integración de microservicios
Para que un microservicio se registre en este servidor Eureka, debe incluir las siguientes dependencias y configuración:
### pom.xml:
```
<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
### application.properties:
```
spring.application.name=nombre-del-servicio
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```
### Clase principal:
```
@SpringBootApplication
@EnableEurekaClient
public class MiServicioApplication {
public static void main(String[] args) {
SpringApplication.run(MiServicioApplication.class, args);
}
}
```
## Monitoreo
El servidor incluye endpoints de Spring Boot Actuator para monitoreo:

- Health: http://localhost:8761/actuator/health
- Info: http://localhost:8761/actuator/info
- Metrics: http://localhost:8761/actuator/metrics

## Estructura del proyecto
```
eureka-server/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/microservice/eurekaserver/
│   │   │       └── EurekaServerApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/microservice/eurekaserver/
│               └── EurekaServerApplicationTests.java
├── .mvn/
├── Dockerfile
├── mvnw
├── mvnw.cmd
└── pom.xml
```
## Autor (es) ✒️

Este sitio fue realizado por:

* **Cristian Lesmes**
* **Jeffer Pinzon**