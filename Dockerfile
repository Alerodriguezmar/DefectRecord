FROM openjdk:17-jdk-slim
RUN mkdir /app
COPY target/defectRecord-1.0.jar /app
COPY /ReportModel.docx /app
EXPOSE 22110
EXPOSE 1433
CMD ["java", "-jar", "/app/defectRecord-1.0.jar"]