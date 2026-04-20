FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY applications/app-service/build/libs/*.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS="-XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]