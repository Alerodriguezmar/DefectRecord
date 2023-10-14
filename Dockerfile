# Utiliza una imagen oficial de Maven para compilar la aplicación
FROM maven:3.8.2-openjdk-17-slim as build

# Establece el directorio de trabajo en /build
WORKDIR /build

# Copia el archivo pom.xml para descargar las dependencias antes de copiar todo el código
COPY pom.xml .
RUN mvn clean package -DskipTests

# Crea un contenedor ligero para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Crea un directorio /app
RUN mkdir /app

# Copia el archivo JAR compilado
COPY --from=build /build/target/defectRecord-1.0.jar /app/

# Copia ReportModel.docx
COPY ReportModel.docx /app

# Expone los puertos
EXPOSE 22110
EXPOSE 1433

# Ejecuta la aplicación
CMD ["java", "-jar", "/app/defectRecord-1.0.jar"]
