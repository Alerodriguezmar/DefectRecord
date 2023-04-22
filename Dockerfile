FROM openjdk:17-jdk-slim
RUN mkdir /app
COPY target/DefectRecord-1.0.jar /app
EXPOSE 22101
CMD ["java", "-jar", "/app/DefectRecord-1.0.jar"]