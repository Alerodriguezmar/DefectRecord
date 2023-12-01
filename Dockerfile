# Usamos una imagen base de Maven 3.9.0 con Java 17 para compilar los proyectos
FROM maven:3.9-amazoncorretto-17 AS builder

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todo el contenido del proyecto1 al directorio de trabajo
COPY .  /app/service

# Compilar y generar el archivo JAR para proyecto2
RUN cd /app/service && mvn clean package -DskipTests

# Cambiamos a una imagen más ligera para la ejecución
FROM eclipse-temurin:17

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos los JAR generados desde la imagen de compilación a la imagen de ejecución
COPY --from=builder /app/service/target/defectRecord-1.0.jar  /app/defectRecord-1.0.jar
COPY --from=builder /app/service/ReportModel.docx /app


# Comando para ejecutar la aplicación (ajusta según tus necesidades)
CMD ["java", "-jar", "/app/defectRecord-1.0.jar"]