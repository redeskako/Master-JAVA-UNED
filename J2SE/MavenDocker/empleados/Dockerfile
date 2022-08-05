FROM openjdk:8-jre-alpine
# copiamos la ruta completa es (llevando es/uned/master/java/empleados)
COPY target/classes / 
# Ejecutamos la orden de java 
CMD ["/usr/bin/java", "Driver"]

# Para subir la imagen en https://learning.oreilly.com/library/view/docker-for-java/9781492042624/ch02.html
# docker login
# docker tag <HashCode> redekako/master-java-uned-2020/empleados
# docker push redeskako/master-java-uned-2020/empleados