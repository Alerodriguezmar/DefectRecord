FROM openjdk:17-jdk-slim
RUN mkdir /app
COPY target/defectRecord-1.0.jar /app
EXPOSE 22110
CMD ["java", "-jar", "/app/defectRecord-1.0.jar"]