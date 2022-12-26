FROM openjdk:11
COPY build/libs/*.jar tdt-backend.jar
ENTRYPOINT ["java","-jar","/tdt-backend.jar"]
