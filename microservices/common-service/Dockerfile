
FROM artifactory.apps.bancolombia.com/evc/stable/amazoncorretto@sha256:16c69c8cb379d6d76e053ec2f7d8e24a9c02887de9a00b200e83631c516aee04

WORKDIR /app

COPY build/libs/common-service.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:MaxRAMPercentage=75","-jar","app.jar"]
