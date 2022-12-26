FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} backend/build/libs/*.jar
ENTRYPOINT ["java","-jar","tdt-backend.jar"]
