# Microservicio de Usuarios (Users Microservice)

## Descripción
Este microservicio es parte de una arquitectura de microservicios diseñada para gestionar la información de los usuarios. Su función principal es proporcionar una API REST para crear, leer, actualizar y eliminar usuarios, así como gestionar la autenticación, autorización y roles de los usuarios.

## Características
- `Registro de Usuarios`: Permite a los nuevos usuarios registrarse en el sistema proporcionando su nombre, correo electrónico y contraseña.
- `Autenticación`: Proporciona un mecanismo de inicio de sesión seguro mediante la verificación de credenciales.
- `Gestión de Perfiles`: Permite a los usuarios actualizar su información personal, como nombre y correo electrónico.
- `Roles y Permisos`: Implementa un sistema de roles (ej: ADMIN, USER) para gestionar el acceso a diferentes funcionalidades del sistema.
- `Recuperación de Contraseña`: Permite a los usuarios recuperar su contraseña a través de un enlace enviado a su correo electrónico.
- `Verificación de Correo Electrónico`: Envía un correo de verificación al registrarse para confirmar la validez del correo electrónico.
- `Listar Usuarios`: Permite a los administradores listar todos los usuarios registrados en el sistema.
- `Búsqueda de Usuarios`: Permite buscar usuarios por ID o por correo electrónico.
- `Eliminación de Usuarios`: Permite a los administradores eliminar usuarios del sistema.
## Tecnologías Utilizadas
- Java 17
- Spring Boot 3.3.10
- Spring Security
- Spring Data MongoDB
- Maven
- Docker
- Eureka Client
- Config Server
- Lombok
## Estructura del Proyecto
```
Users/
│
├── src/
│   ├── main/
│   │   ├── java/com/microservice/users/
│   │   │   ├── controller/             # Controladores REST
│   │   │   ├── dto/                    # Objetos de transferencia de datos
│   │   │   ├── model/                  # Entidades de dominio
│   │   │   ├── repository/             # Repositorios para acceso a datos
│   │   │   ├── service/                # Lógica de negocio
│   │   │   └── UsersApplication.java    # Clase principal para iniciar la aplicación
│   │   └── resources/
│   │       └── application.properties   # Configuración de la aplicación
│   └── test/
│       └── java/com/microservice/users/
│           └── UsersApplicationTests.java  # Pruebas de la aplicación
│
├── Dockerfile                          # Configuración para Docker
├── mvnw                                # Script de Maven Wrapper para Linux/Mac
├── mvnw.cmd                            # Script de Maven Wrapper para Windows
└── pom.xml                             # Configuración de Maven y dependencias
```

## Instalación
### Clonar el repositorio:

```bash
git clone https://github.com/lezmez/Microservicios-Sistema-Educativo.git
```
### Navegar al directorio del microservicio de Usuarios:

```bash
cd Microservicios-Sistema-Educativo/Users
```
### Construir el proyecto:

``` bash
mvn clean install
```
### Ejecutar la aplicación:

```bash
mvn spring-boot:run
```
## Configuración
Archivo de configuración: `application.properties`
- Configura la conexión a la base de datos, puertos y otros parámetros necesarios.

Ejemplo de configuración:

```
properties

spring.application.name=users
spring.data.mongodb.uri=mongodb://localhost:27017/usersdb
server.port=8080

management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always

spring.config.import=optional:configserver:http://localhost:8888
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.instance.prefer-ip-address=true
```
## Modelo de Datos
El microservicio gestiona la entidad User con los siguientes atributos:

- `userId`: Identificador único del usuario (generado automáticamente por MongoDB).
- `name`: Nombre completo del usuario.
- `email`: Correo electrónico único del usuario (debe ser validado).
- `password`: Contraseña del usuario (almacenada de forma segura).
- `roles`: Lista de roles asignados al usuario (ej: ADMIN, USER).
- `isVerified`: Indica si el correo electrónico del usuario ha sido verificado.
- `createdAt`: Fecha de creación del usuario.
- `updatedAt`: Fecha de la última actualización de la información del usuario.
## Uso
Una vez que la aplicación esté en ejecución, puedes interactuar con el microservicio a través de los endpoints REST definidos.

## Endpoints
```
Método     |      Endpoint                   |      Descripción
___________|_________________________________|_________________________________
POST       |      /api/users/register        |      Registra un nuevo usuario.
POST       |      /api/users/login           |      Inicia sesión de un usuario.
GET        |      /api/users/{id}            |      Obtiene la información de un usuario.
PUT        |      /api/users/{id}            |      Actualiza la información de un usuario.
DELETE     |      /api/users/{id}            |      Elimina un usuario.
POST       |      /api/users/recover         |      Recupera la contraseña del usuario.
GET        |      /api/users/verify/{email}  |      Verifica el correo electrónico del usuario.
GET        |      /api/users                 |      Lista todos los usuarios (solo para administradores).
GET        |      /api/users/search          |      Busca usuarios por ID o correo electrónico.
```
## Ejemplos de Uso
### Registro de un nuevo usuario
```bash
curl -X POST http://localhost:8080/api/users/register \
-H "Content-Type: application/json" \
-d '{"name":"John Doe", "email":"johndoe@example.com", "password":"password"}'
```
### Inicio de sesión de un usuario
```bash
curl -X POST http://localhost:8080/api/users/login \
-H "Content-Type: application/json" \
-d '{"email":"johndoe@example.com", "password":"password"}'
```
### Obtención de la información de un usuario
```bash
curl -X GET http://localhost:8080/api/users/1
```
### Actualización de la información de un usuario
```bash
curl -X PUT http://localhost:8080/api/users/1 \
-H "Content-Type: application/json" \
-d '{"name":"Jane Doe", "email":"janedoe@example.com"}'
```
### Eliminación de un usuario
```bash
curl -X DELETE http://localhost:8080/api/users/1
```
### Recuperación de contraseña
```bash
curl -X POST http://localhost:8080/api/users/recover \
-H "Content-Type: application/json" \
-d '{"email":"johndoe@example.com"}'
```
### Verificación de correo electrónico
```bash
curl -X GET http://localhost:8080/api/users/verify/johndoe@example.com
```
### Listar todos los usuarios
```bash
curl -X GET http://localhost:8080/api/users
```
### Búsqueda de usuarios
```bash
curl -X GET http://localhost:8080/api/users/search?email=johndoe@example.com
```
## Autor (es) ✒️

Este sitio fue realizado por:

* **Cristian Lesmes**
* **Jeffer Pinzon**
