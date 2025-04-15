# Servidor de Configuración
## Descripción General
Este proyecto es un servidor de configuración construido con Spring Cloud Config Server que proporciona gestión centralizada de configuraciones para microservicios. Permite externalizar la configuración en un sistema distribuido, con propiedades de configuración almacenadas en un repositorio Git.
## Características

- Gestión centralizada de configuración para microservicios
- Repositorio de configuración respaldado por Git
- Integración con Spring Cloud
- Integración con Eureka Discovery Service
- Endpoints de monitoreo de salud
- Mecanismo de reintento para recuperación de configuraciones
- Soporte para Docker

## Prerrequisitos

- Java 17
- Maven 3.9+
- Git

## Stack Tecnológico

- Spring Boot 3.3.10
- Spring Cloud 2023.0.5
- Spring Cloud Config Server
- Spring Cloud Netflix Eureka Client

## Configuración
El Servidor de Configuración se conecta a un repositorio Git para servir archivos de configuración a aplicaciones cliente:
```
server.port=8888
spring.application.name=config-server

# Configuración del repositorio Git
spring.cloud.config.server.git.uri=https://github.com/JefferPinzon/prueba1
spring.cloud.config.server.git.clone-on-start=true
spring.cloud.config.server.git.default-label=main

# Salud y monitoreo
management.endpoints.web.exposure.include=health,info,env
management.endpoint.health.show-details=always

# Configuración de reintentos
spring.cloud.config.fail-fast=true
spring.cloud.config.retry.initial-interval=1000
spring.cloud.config.retry.max-interval=2000
spring.cloud.config.retry.max-attempts=5
```
## Compilación de la Aplicación
Puedes compilar la aplicación usando Maven:
```bash
./mvnw clean install
```
## Ejecución de la Aplicación
### Usando Maven
```bash
./mvnw spring-boot:run
```
### Usando Java
```bash
java -jar target/config-server-0.0.1-SNAPSHOT.jar
```
### Usando Docker
Construir la imagen Docker:
```bash
docker build -t config-server .
```
### Ejecutar el contenedor Docker:
```bash
docker run -p 8888:8888 config-server
```
## Acceso al Servidor de Configuración
Una vez que la aplicación está en ejecución, puedes acceder a las propiedades de configuración utilizando el siguiente patrón de URL:
```
http://localhost:8888/{nombre-aplicacion}/{perfil}[/{etiqueta}]
```
Donde:

- `{nombre-aplicacion}` es el nombre de la aplicación cliente
- `{perfil}` es el perfil activo (ej. `dev`, `test`, `prod`)
- `{etiqueta}` es opcional y se refiere a una rama específica, etiqueta o commit en el repositorio Git (por defecto: `main`)

## Monitoreo de Salud
La aplicación expone varios endpoints actuator para monitoreo:
```
http://localhost:8888/actuator/health
http://localhost:8888/actuator/info
http://localhost:8888/actuator/env
```
## Estructura del Proyecto
```
config-server/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/microservice/configserver/
│   │   │       └── ConfigServerApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/microservice/configserver/
│               └── ConfigServerApplicationTests.java
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── Dockerfile
├── mvnw
├── mvnw.cmd
└── pom.xml
```
## Autor (es) ✒️

Este sitio fue realizado por:

* **Cristian Lesmes**
* **Jeffer Pinzon**