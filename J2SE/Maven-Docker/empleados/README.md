# Segundo ejemplo de Maven con Docker

## Objetivo

 * Crear una lista de personas con las funciones mínimas de trabajo.
 * Para ello creado la clase Persona (se llama bean). que contiene la información de una persona.
 
## Código

### Dockerfile

# Alpine Linux with OpenJDK JRE
```
# Optamos por la imagen de alpine openjdk
FROM openjdk:8-jre-alpine
# copiamos la ruta completa es (llevando es/uned/master/java/empleados)
COPY target/es /es 
# Ejecutamos la orden de java 
CMD ["/usr/bin/java", "es.uned.master.java.empleados.Driver"]
```

 * Para subir la imagen en https://learning.oreilly.com/library/view/docker-for-java/9781492042624/ch02.html

```
docker login
docker tag <HashCode> redekako/master-java-uned-2020/empleados
docker push redeskako/master-java-uned-2020/empleados
```
