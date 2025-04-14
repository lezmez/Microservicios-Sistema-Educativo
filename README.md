# Microservicios Sistema Educativo

## Descripción
Este proyecto implementa un sistema educativo basado en una arquitectura de microservicios, diseñado para gestionar estudiantes, docentes, asignaturas y matrículas de manera distribuida y escalable. Cada microservicio es independiente y se comunica con los demás a través de APIs REST, utilizando tecnologías modernas como Spring Boot, Spring Cloud y MongoDB.

## Arquitectura del Sistema
### Componentes Principales

### 1. Servidor de Configuración (Config Server)
- Gestión centralizada de configuraciones para todos los microservicios
- Basado en un repositorio Git para almacenar archivos de configuración

### 2. Servidor de Descubrimiento (Eureka Server)
- Registro y descubrimiento de servicios
- Permite la comunicación entre microservicios sin necesidad de URLs hardcodeadas

### 3. Microservicio de Usuarios
- Gestión de estudiantes y docentes
- Autenticación y autorización mediante JWT
- Perfiles de usuario y control de acceso basado en roles

### 4. Microservicio de Asignaturas
- CRUD de materias académicas
- Gestión de información relacionada con las asignaturas

### 5. Microservicio de Matrículas
- Registro de estudiantes en materias
- Comunicación con los microservicios de usuarios y asignaturas
- Gestión de las relaciones entre estudiantes y asignaturas

### 6. Monitor Admin
- Dashboard de monitorización para supervisar el estado de los microservicios
- Visualización de métricas y estado de salud

## Tecnologías Utilizadas
- Lenguaje de programación: Java 17
- Framework: Spring Boot 3.3.10, Spring Cloud 2023.0.5
- Base de datos: MongoDB
- Comunicación entre servicios: Feign Client
- Gestión de dependencias: Maven
- Contenedorización: Docker
- Seguridad: JWT, Spring Security
- Monitorización: Spring Boot Actuator
- Descubrimiento de servicios: Eureka Server
- Configuración centralizada: Config Server

## Estructura del Proyecto
```
Microservicios-Sistema-Educativo/
│
├── .idea/                     # Configuración del IDE
│
├── config-server/             # Servidor de configuración centralizada
│
├── eureka-server/             # Servidor de registro y descubrimiento
│
├── monitor-admin/             # Dashboard de monitorización
│
├── registration/              # Microservicio de matrículas
│
├── subjects/                  # Microservicio de asignaturas
│
├── users/                     # Microservicio de usuarios
│
├── .gitattributes             # Configuración de atributos Git
│
├── docker-compose.yml         # Configuración para orquestación con Docker
│
└── README.md                  # Documentación general del proyecto
```
## Requisitos Previos

- JDK 17 o superior
- Maven 3.6 o superior
- MongoDB
- Docker y Docker Compose (para despliegue)
- Git

## Instalación y Ejecución
## Opción 1: Ejecución Individual de Servicios

### Clonar el repositorio
```bash
git clone https://github.com/lezmez/Microservicios-Sistema-Educativo.git
cd Microservicios-Sistema-Educativo
```
### Iniciar los servicios en orden
```bash
# 1. Config Server
cd config-server
mvn spring-boot:run

# 2. Eureka Server
cd ../eureka-server
mvn spring-boot:run

# 3. Microservicios (en ventanas de terminal separadas)
cd ../users
mvn spring-boot:run

cd ../subjects
mvn spring-boot:run

cd ../registration
mvn spring-boot:run

# 4. Monitor Admin (opcional)
cd ../monitor-admin
mvn spring-boot:run
```
## Opción 2: Ejecución con Docker Compose

### Clonar el repositorio
```bash
git clone https://github.com/lezmez/Microservicios-Sistema-Educativo.git
cd Microservicios-Sistema-Educativo
```
### Construir y ejecutar con Docker Compose
```bash
docker-compose up -d
```

## Flujo de Comunicación
1. El usuario se autentica a través del microservicio de usuarios y obtiene un token JWT
2. El token JWT se utiliza para las llamadas posteriores a los diferentes microservicios
3. Al registrar una matrícula, el microservicio de matrículas se comunica con:
    - Microservicio de usuarios para validar el estudiante
    - Microservicio de asignaturas para validar la asignatura
4. Todos los microservicios se registran en Eureka para facilitar el descubrimiento
5. Todas las configuraciones se obtienen del Config Server

## Endpoints Principales
### Microservicio de Usuarios
- `POST /api/users/register`: Registra un nuevo usuario
- `POST /api/users/login`: Autenticación y obtención de token JWT
- `GET /api/users`: Lista todos los usuarios (admin)
- `GET /api/users/{id}`: Obtiene información de un usuario específico

### Microservicio de Asignaturas
- `GET /api/subjects`: Lista todas las asignaturas
- `GET /api/subjects/{id}`: Obtiene información de una asignatura específica
- `POST /api/subjects`: Crea una nueva asignatura

### Microservicio de Matrículas
- `POST /api/registrations`: Registra un estudiante en una asignatura
- `GET /api/registrations`: Lista todas las matrículas
- `GET /api/registrations/student/{userId}`: Lista matrículas por estudiante
- `GET /api/registrations/subject/{subjectId}`: Lista estudiantes por asignatura

### Monitorización
Para acceder al panel de monitorización:
- URL: `http://localhost:8090`
- Esta interfaz muestra el estado de todos los microservicios registrados, métricas de rendimiento y detalles de salud.

### Seguridad
El sistema implementa autenticación y autorización mediante JWT:
- Los usuarios se autentican en el microservicio de usuarios
- El token JWT obtenido debe incluirse en las cabeceras de las peticiones a los demás microservicios
- Los roles de usuario determinan el acceso a diferentes funcionalidades

### Pruebas
Para ejecutar las pruebas de cada microservicio:
```bash
cd [nombre-microservicio]
mvn test
```
La colección de Postman para pruebas de integración está disponible en el directorio `docs/postman`.
### Despliegue en Producción
Para un despliegue en producción, se recomienda:
- Configurar HTTPS para todas las comunicaciones
- Ajustar las configuraciones de cada microservicio para entornos de producción
- Implementar múltiples instancias de cada microservicio para alta disponibilidad
- Utilizar un balanceador de carga delante de los microservicios

## Autor (es) ✒️

Este sitio fue realizado por:

* **Cristian Lesmes**
* **Jeffer Pinzon**
