FROM openjdk
WORKDIR /app
COPY /src/main/resources/application.properties /app/application.properties
COPY target/pagamentos-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-Dspring.config.location=/app/application.properties", "-jar", "app.jar"]