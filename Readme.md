# AREP Lab 01 - Web Service

Aplicación web la cual incluye HTML, CSS y JavaScrpit para mostrar un servicio web mediante el pedido de servicios.

## Inicio

### Requisitos

Antes de ejecutar el proyecto, asegúrese de contar con las siguientes herramientas instaladas:

- Maven - Administrador de dependencias y administrador del ciclo de vida del proyecto
- Java - Ambiente de desarrollo
-  Git - Sistema de control de versiones y descarga del repositorio

### Instalación y configuración

Para ejecutar el programa, siga los siguientes pasos:
1. Clone el repositorio con el siguiente comando:
```
https://github.com/FlypZed/Lab01-WebServer.git
```
2. Acceda a la carpeta del repositorio descargado y ejecute el siguiente comando para iniciar la aplicación:
```
mvn clean package exec:java -D"exec.mainClass"="app.App"
```

3. Una vez en ejecución, acceda desde su navegador al siguiente enlace:

* http://localhost:35000/app/home.html

![image](https://github.com/user-attachments/assets/42af8f3f-b4fb-4591-9a15-d28019e95a57)

Si intenta acceder a un servicio inexistente, se redirigirá a una página de error 404. Por ejemplo:

* http://localhost:35000/app/a.html

![image](https://github.com/user-attachments/assets/308dafee-213d-4b14-a12c-70e5812e118b)

Para visualizar un archivo específico, como una hoja de estilos, use: 

* http://localhost:35000/app/home.css

## Documentación

La documentación del proyecto se encuentra en la carpeta javadoc. Para regenerarla, ejecute el siguiente comando:

```
mvn javadoc:javadoc
```

## Tecnologías utilizadas

* [Maven](https://maven.apache.org/) -  Administrador de dependencias.

## Versionado

Versión 1.0

## Autores

* Andres Felipe Parra Quiroga

## Detalles Técnicos

El sistema sigue una arquitectura basada en API Rest. Para la gestión de caché, se implementa el patrón de diseño Singleton, asegurando que solo exista una única instancia de caché en el servidor.

* Extensibilidad: Se implementa una única clase PagesServices, la cual utiliza el patrón Singleton. Esto permite acceder a los recursos almacenados en disco sin necesidad de crear un servicio individual por cada archivo.

* Patrones de diseño: Se aplican los patrones Fachada y Singleton para mejorar la organización y reutilización del código.

* Modularización: Todas las clases están diseñadas bajo el principio de responsabilidad única, facilitando la escalabilidad y mantenimiento del código en caso de futuras extensiones.

