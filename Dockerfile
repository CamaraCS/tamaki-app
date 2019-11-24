FROM openjdk:8-jdk

MAINTAINER Renato T Tamaki <renato.tamaki@gmail.com>

COPY target/*.jar /app.jar

EXPOSE 8090
HEALTHCHECK --interval=10s --timeout=2s --retries=6 CMD curl --fail http://localhost:8090/management/health || exit 1

CMD ["java", "-jar", "-Dspring.profiles.active=default", "/app.jar"]
