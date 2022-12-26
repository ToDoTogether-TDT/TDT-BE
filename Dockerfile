FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} tdt-backend.jar
ENTRYPOINT ["java","-jar","tdt-backend.jar"]