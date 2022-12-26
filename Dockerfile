FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar tdt-backend.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/tdt-backend.jar"]
