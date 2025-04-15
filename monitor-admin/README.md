# Spring Boot Admin Monitor
## Descripción
Este proyecto implementa un servidor de Spring Boot Admin para supervisar y gestionar aplicaciones Spring Boot en un entorno de microservicios. Spring Boot Admin proporciona una interfaz web para visualizar y administrar el estado y la configuración de múltiples instancias de aplicaciones Spring Boot registradas en un entorno de Eureka.
## Tecnologías utilizadas

- Java 17
- Spring Boot 3.4.4
- Spring Boot Admin 3.4.5
- Spring Cloud 2024.0.1
- Netflix Eureka Client
- Maven 3.9.9
- Docker

## Prerequisitos

- JDK 17 o superior
- Maven 3.6.3 o superior (opcional, ya que se incluye Maven Wrapper)
- Docker (opcional, para ejecución en contenedor)
- Servidor Eureka ejecutándose en http://localhost:8761

## Configuración
El servidor Spring Boot Admin está configurado para:

- Ejecutarse en el puerto 8088
- Registrarse como cliente en un servidor Eureka
- Descubrir automáticamente otras aplicaciones registradas en Eureka

## Instalación y ejecución
### Usando Maven Wrapper
```bash
# Clonar el repositorio
git clone <url-del-repositorio>
cd monitor-admin

# Compilar el proyecto
./mvnw clean package

# Ejecutar la aplicación
./mvnw spring-boot:run
```
Usando Docker
```bash
# Construir la imagen Docker
docker build -t monitor-admin .

# Ejecutar el contenedor
docker run -p 8088:8088 monitor-admin
```
## Acceso a la interfaz de Spring Boot Admin
Una vez que la aplicación esté en ejecución, podrá acceder a la interfaz de Spring Boot Admin en:
```
http://localhost:8088
```
## Funcionalidades principales

- Monitoreo de servicios registrados en Eureka
- Dashboard centralizado para visualización de métricas
- Detección automática de servicios
- Visualización de logs, threads, y memoria
- Monitoreo de estado de salud (health endpoints)
- Visualización de propiedades de configuración

## Integración de aplicaciones a monitorear
Para que una aplicación se registre en Spring Boot Admin, debe:

- Añadir la dependencia spring-boot-admin-starter-client en su archivo pom.xml
- Configurar la propiedad spring.boot.admin.client.url para apuntar a este servidor
- Estar registrada en el mismo servidor Eureka

## Estructura del proyecto
```
monitor-admin/
├── .mvn/wrapper/         # Configuración de Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/         # Código fuente
│   │   └── resources/    # Archivos de configuración
│   └── test/             # Pruebas unitarias
├── Dockerfile            # Configuración para Docker
├── mvnw                  # Maven Wrapper para Unix
├── mvnw.cmd              # Maven Wrapper para Windows
└── pom.xml               # Configuración de dependencias
```
## Autor (es) ✒️

Este sitio fue realizado por:

* **Cristian Lesmes**
* **Jeffer Pinzon**