# Microservicio de Asignaturas (Subject Microservice)
## Descripción
Este microservicio forma parte de una arquitectura de microservicios y gestiona la información relacionada con asignaturas académicas. Permite crear, listar y buscar asignaturas por ID, utilizando MongoDB como base de datos y se integra con un servidor Eureka para el descubrimiento de servicios.
## Tecnologías utilizadas

- Java 17
- Spring Boot 3.3.10
- Spring Data MongoDB
- Spring Cloud Netflix Eureka Client
- Lombok
- Maven

## Estructura del proyecto

```
Estructura del proyecto
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/microservice/subjects/
│   │   │       ├── controller/          # Controladores REST
│   │   │       ├── model/               # Entidades y modelos de datos
│   │   │       ├── repository/          # Interfaces de repositorio para MongoDB
│   │   │       ├── service/             # Lógica de negocio
│   │   │       └── SubjectsApplication.java  # Clase principal
│   │   └── resources/
│   │       └── application.properties   # Configuración de la aplicación
│   └── test/
│       └── java/
│           └── com/microservice/subjects/
│               └── SubjectsApplicationTests.java
├── .mvn/wrapper/                        # Configuración de Maven Wrapper
├── Dockerfile                           # Configuración para crear imagen Docker
├── mvnw                                 # Script Maven Wrapper para Linux/Mac
├── mvnw.cmd                             # Script Maven Wrapper para Windows
└── pom.xml                              # Configuración de dependencias Maven
```
## Modelo de datos
El microservicio gestiona la entidad Subject con los siguientes atributos:

- `subjectId`: Identificador único de la asignatura (generado automáticamente por MongoDB)
- `subjectCode`: Código de la asignatura
- `subjectName`: Nombre de la asignatura
- `subjectCredits`: Número de créditos de la asignatura

## API REST
### Endpoints disponibles
```
Método HTTP    |    Endpoint            |  Descripción
_______________|________________________|________________________________________________
GET            |    /api/subjects       |  Obtiene la lista completa de asignaturas
GET            |    /api/subjects/{id}  |  Obtiene una asignatura específica por su ID
POST           |    /api/subjects       |  Crea una nueva asignatura
```
## Ejemplos de uso
### Crear una asignatura
```bash
curl -X POST http://localhost:8082/api/subjects \
-H "Content-Type: application/json" \
-d '{
"subjectCode": "MAT101",
"subjectName": "Cálculo I",
"subjectCredits": 5
}'
```
### Obtener todas las asignaturas
```bash
curl -X GET http://localhost:8082/api/subjects
```
### Obtener una asignatura por ID
```bash
curl -X GET http://localhost:8082/api/subjects/123456789
```
## Configuración
El archivo `application.properties` contiene la configuración principal:
```bash 
properties
spring.application.name=subjects
spring.data.mongodb.uri=mongodb://localhost:27017/subjectdb
server.port=8082

management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always

spring.config.import=optional:configserver:http://localhost:8888
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.instance.prefer-ip-address=true
```
## Requisitos previos

- Java 17 o superior
- MongoDB corriendo en localhost:27017 (o configurado según el archivo application.properties)
- Servidor Eureka corriendo en localhost:8761 (para registro de servicios)

## Ejecución
### Usando Maven
```bash
./mvnw spring-boot:run
```
### Usando Docker
Primero, construya la imagen Docker:
```bash
./mvnw clean package
docker build -t microservice/subjects .
```
Luego, ejecute el contenedor:
```bash
docker run -p 8082:8082 microservice/subjects
```
## Integración con otros microservicios
Este microservicio está diseñado para integrarse con otros servicios a través del registro Eureka. Asegúrese de que el servidor Eureka esté en funcionamiento para habilitar el descubrimiento de servicios.
## Monitoreo y Actuator
El microservicio expone endpoints de Spring Boot Actuator para monitoreo y estado. Puede acceder a ellos a través de:
```
http://localhost:8082/actuator
```
Pruebas
Para ejecutar las pruebas unitarias:
```bash
./mvnw test
```
## Autor (es) ✒️

Este sitio fue realizado por:

* **Cristian Lesmes**
* **Jeffer Pinzon**