# Microservicio de Matricula (Registration Microservice)
## Descripción
El microservicio de Registro (Registration) es parte de una arquitectura de microservicios diseñada para gestionar las matriculaciones de estudiantes en asignaturas. Este servicio se encarga de vincular usuarios con asignaturas, manteniendo un registro de estas relaciones y comunicándose con otros microservicios para obtener información detallada.
## Tecnologías

- Java 17
- Spring Boot 3.3.10
- Spring Cloud 2023.0.5
- MongoDB (base de datos NoSQL)
- Feign Client (comunicación entre microservicios)
- Eureka Client (descubrimiento de servicios)
- Config Server (configuración centralizada)
- Maven (gestión de dependencias)
- Docker (contenerización)

## Arquitectura
Este microservicio sigue los principios de una arquitectura de microservicios y se integra con:

- `Servicio de Usuarios`: Para obtener información de los estudiantes
- `Servicio de Asignaturas`: Para obtener información de las materias
- `Servidor Eureka`: Para registro y descubrimiento de servicios
- `Config Server`: Para configuración centralizada

## Estructura del Proyecto
```
registration/
│
├── .mvn/wrapper/                       # Configuración del wrapper de Maven
│   └── maven-wrapper.properties
│
├── src/
│   ├── main/
│   │   ├── java/com/microservice/registration/
│   │   │   ├── client/                 # Clientes para comunicación con otros servicios
│   │   │   │   ├── FeingClientInterceptor.java   # Interceptor para propagar headers
│   │   │   │   ├── SubjectClient.java            # Cliente para el servicio de asignaturas
│   │   │   │   └── UserClient.java               # Cliente para el servicio de usuarios
│   │   │   │
│   │   │   ├── controller/             # Controladores REST
│   │   │   │   └── RegistrationController.java   # Endpoints de la API
│   │   │   │
│   │   │   ├── dto/                    # Objetos de transferencia de datos
│   │   │   │   ├── RegistrationDto.java          # DTO para peticiones de registro
│   │   │   │   └── RegistrationResponseDto.java  # DTO para respuestas con info detallada
│   │   │   │
│   │   │   ├── entity/                 # Entidades de dominio
│   │   │   │   └── Registration.java             # Entidad principal de registro
│   │   │   │
│   │   │   ├── repository/             # Repositorios para acceso a datos
│   │   │   │   └── RegistrationRepository.java   # Operaciones con la BD
│   │   │   │
│   │   │   ├── service/                # Lógica de negocio
│   │   │   │   └── RegistrationService.java      # Servicio principal
│   │   │   │
│   │   │   └── RegistrationApplication.java  # Clase principal para iniciar la aplicación
│   │   │
│   │   └── resources/
│   │       └── application.properties   # Configuración de la aplicación
│   │
│   └── test/
│       └── java/com/microservice/registration/
│           └── RegistrationApplicationTests.java  # Pruebas de la aplicación
│
├── .gitattributes                      # Configuración de atributos Git
├── .gitignore                          # Archivos ignorados por Git
├── Dockerfile                          # Configuración para Docker
├── mvnw                                # Script de Maven Wrapper para Linux/Mac
├── mvnw.cmd                            # Script de Maven Wrapper para Windows
├── pom.xml                             # Configuración de Maven y dependencias
└── README.md                           # Documentación del proyecto
```
## Detalles de Componentes

### Controllers (Controladores)
#### RegistrationController
- **Ruta Base**: `/api/registrations`
- **Endpoints**:
    - `POST /api/registrations`: Registrar estudiante en asignatura
    - `GET /api/registrations`: Listar todas las matriculaciones
    - `GET /api/registrations/student/{userId}`: Buscar por estudiante
    - `GET /api/registrations/subject/{subjectId}`: Buscar por asignatura

### DTOs (Objetos de Transferencia de Datos)
#### RegistrationDto
- Estructura para solicitudes de nueva matriculación
- Campos:
    - `userId`: ID del estudiante
    - `subjectId`: ID de la asignatura

#### RegistrationResponseDto
- Estructura completa con información detallada
- Campos:
    - `id`: ID de la matriculación
    - `user`: Objeto con datos del usuario
    - `subject`: Objeto con datos de la asignatura

### Entities (Entidades)
#### Registration
- Entidad principal almacenada en MongoDB
- Campos:
    - `id`: Identificador único (generado automáticamente)
    - `userId`: ID del usuario/estudiante
    - `subjectId`: ID de la asignatura

### Repositories (Repositorios)
#### RegistrationRepository
- Extiende `MongoRepository<Registration, String>`
- Métodos personalizados:
    - `findByUserId(String userId)`: Buscar por ID de usuario
    - `findBySubjectId(String subjectId)`: Buscar por ID de asignatura

### Services (Servicios)
#### RegistrationService
- Implementa la lógica de negocio
- Métodos principales:
    - `registerStudent(String userId, String subjectId)`: Registrar nuevo estudiante
    - `listAll()`: Listar todas las matriculaciones
    - `findByStudent(String userId)`: Buscar por estudiante
    - `findBySubject(String subjectId)`: Buscar por asignatura
    - `convertToResponseDto(Registration)`: Método privado para conversión

### Clients (Clientes)
#### UserClient
- Cliente Feign para comunicación con el servicio de usuarios
- Método: `getUserById(String id)`

#### SubjectClient
- Cliente Feign para comunicación con el servicio de asignaturas
- Método: `getSubjectById(String id)`

#### FeingClientInterceptor
- Interceptor para propagar encabezados de autenticación
- Método: `apply(RequestTemplate template)`

## API en Detalle

### Registrar un estudiante en una asignatura
```
POST /api/registrations
```
- **Descripción**: Crea una nueva matriculación vinculando un estudiante con una asignatura
- **Cuerpo de la solicitud**:
  ```json
  {
    "userId": "string",
    "subjectId": "string"
  }
  ```
- **Respuesta exitosa**:
    - Código: 200 OK
    - Contenido: Mensaje de confirmación (ej. "Student Juan Pérez registered in Mathematics")
- **Posibles errores**:
    - 404: Usuario o asignatura no encontrados
    - 400: Solicitud inválida (falta de campos)
    - 500: Error interno del servidor

### Listar todas las matriculaciones
```
GET /api/registrations
```
- **Descripción**: Obtiene todas las matriculaciones con información detallada
- **Respuesta exitosa**:
    - Código: 200 OK
    - Contenido: Array de objetos RegistrationResponseDto
  ```json
  [
    {
      "id": "string",
      "user": {
        "id": "string",
        "nombre": "string"
      },
      "subject": {
        "id": "string",
        "subjectName": "string"
      }
    }
  ]
  ```
- **Posibles errores**:
    - 500: Error interno del servidor

### Buscar matriculaciones por estudiante
```
GET /api/registrations/student/{userId}
```
- **Descripción**: Obtiene todas las matriculaciones de un estudiante específico
- **Parámetros de ruta**:
    - `userId`: ID del estudiante a consultar
- **Respuesta exitosa**:
    - Código: 200 OK
    - Contenido: Array de objetos RegistrationResponseDto con todas las asignaturas del estudiante
- **Posibles errores**:
    - 404: Usuario no encontrado
    - 500: Error interno del servidor

### Buscar matriculaciones por asignatura
```
GET /api/registrations/subject/{subjectId}
```
- **Descripción**: Obtiene todos los estudiantes matriculados en una asignatura específica
- **Parámetros de ruta**:
    - `subjectId`: ID de la asignatura a consultar
- **Respuesta exitosa**:
    - Código: 200 OK
    - Contenido: Array de objetos RegistrationResponseDto con todos los estudiantes de la asignatura
- **Posibles errores**:
    - 404: Asignatura no encontrada
    - 500: Error interno del servidor

## Configuración en Detalle

### Configuración de MongoDB
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/registrationdb
```
- **Base de datos**: registrationdb
- **Host**: localhost
- **Puerto**: 27017

### Configuración del Puerto
```properties
server.port=8083
```
- El servicio escucha en el puerto 8083

### Configuración de Eureka
```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.instance.prefer-ip-address=true
```
- **URL de Eureka**: http://localhost:8761/eureka
- **Preferencia de IP**: Usa dirección IP en lugar de hostname para registro

### Configuración de Config Server
```properties
spring.config.import=optional:configserver:http://localhost:8888
```
- **URL de Config Server**: http://localhost:8888
- **Importación opcional**: No falla si Config Server no está disponible

## Instrucciones de Construcción y Ejecución

### Requisitos Previos
- JDK 17 o superior
- Maven 3.6 o superior (o usar el wrapper incluido)
- MongoDB (instalado localmente o contenedor)
- Servicios dependientes ejecutándose (users, subjects, eureka, config-server)

### Construir con Maven
```bash
# Usando Maven instalado
mvn clean package

# Usando Maven Wrapper
./mvnw clean package    # Linux/Mac
mvnw.cmd clean package  # Windows
```

### Ejecutar localmente
```bash
# Usando Maven instalado
mvn spring-boot:run

# Usando Maven Wrapper
./mvnw spring-boot:run    # Linux/Mac
mvnw.cmd spring-boot:run  # Windows

# Usando el JAR directamente
java -jar target/registration-0.0.1-SNAPSHOT.jar
```

### Construir y ejecutar con Docker
```bash
# Construir imagen
docker build -t microservice/registration:latest .

# Ejecutar contenedor
docker run -p 8083:8083 microservice/registration:latest
```

### Ejecutar con Docker Compose (ejemplo)
```yaml
# docker-compose.yml (ejemplo)
version: '3'
services:
  registration:
    build: .
    ports:
      - "8083:8083"
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/registrationdb
      - EUREKA_CLIENT_SERVICE-URL_DEFAULTZONE=http://eureka:8761/eureka
      - SPRING_CONFIG_IMPORT=optional:configserver:http://config-server:8888
    depends_on:
      - mongodb
      - eureka
      - config-server
```

## Consideraciones para Producción

### Seguridad
- Implementar autenticación OAuth2/JWT
- Configurar HTTPS
- Restringir acceso a endpoints sensibles
- Implementar validación de entrada
- Proteger contra ataques comunes (CSRF, XSS)

### Resiliencia
- Implementar Circuit Breaker (usando Resilience4j)
- Configurar timeout adecuados para llamadas Feign
- Implementar estrategia de reintentos
- Manejar fallos de comunicación con otros servicios

### Monitorización
- Habilitar métricas con Spring Boot Actuator
- Integrar con sistemas de monitorización (Prometheus, Grafana)
- Configurar healthchecks
- Implementar trazabilidad distribuida (Sleuth, Zipkin)

### Escalabilidad
- Desplegar múltiples instancias
- Configurar balanceo de carga
- Implementar cachés si es necesario
- Optimizar consultas a MongoDB

### Logs
- Configurar niveles de log adecuados
- Centralizar logs (ELK, Graylog)
- Incluir información de contexto en logs

## Pruebas

### Pruebas Unitarias
Ejecutar:
```bash
./mvnw test
```

### Pruebas con Postman/cURL
Ejemplos de peticiones:

#### Registrar estudiante
```bash
curl -X POST http://localhost:8083/api/registrations \
  -H "Content-Type: application/json" \
  -d '{"userId":"1", "subjectId":"101"}'
```

#### Listar todas las matriculaciones
```bash
curl -X GET http://localhost:8083/api/registrations
```

#### Buscar por estudiante
```bash
curl -X GET http://localhost:8083/api/registrations/student/1
```

#### Buscar por asignatura
```bash
curl -X GET http://localhost:8083/api/registrations/subject/101
```

## Autor (es) ✒️

Este sitio fue realizado por:

* **Cristian Lesmes**
* **Jeffer Pinzon**