FROM eclipse-temurin:21-jdk-alpine 
VOLUME /tmp

# Apuntamos a la ruta real del scaffold de Bancolombia
COPY applications/app-service/build/libs/*.jar common-service.jar

ENV JAVA_OPTS=" -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

# El usuario appuser debe existir en la imagen base o crearse. 
# Si falla, comenta esta línea temporalmente para la PoC
# USER appuser 

# Simplificamos el ENTRYPOINT para que sea más robusto
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /common-service.jar"]